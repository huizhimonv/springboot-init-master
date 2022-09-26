package com.jcjc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcjc.project.common.R;
import com.jcjc.project.dto.DishDto;

import com.jcjc.project.model.entity.Category;
import com.jcjc.project.model.entity.Dish;
import com.jcjc.project.model.entity.DishFlavor;
import com.jcjc.project.service.CategoryService;
import com.jcjc.project.service.DishFlavorService;
import com.jcjc.project.service.DishService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 神原秋人
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());

        dishService.saveWithFlavor(dishDto);
        //清理某个分类下的菜品缓存
        String keys = "dish_"+dishDto.getCategoryId()+"_1";
        redisTemplate.delete(keys);

        return R.success("新增菜品成功");
    }

    /**
     * 菜品分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //分页构造器
        Page<Dish> pageInfo = new Page<>(page,pageSize);

        Page<DishDto> dishDtoPage= new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(name != null,Dish::getName,name);

        queryWrapper.orderByDesc(Dish::getUpdateTime);
        //执行分页查询
        dishService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list= records.stream().map((item) -> {
           DishDto dishDto = new DishDto();
           BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String catagoryName = category.getName();
                dishDto.setCategoryName(catagoryName);
            }
           return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);

    }

    /**
     * 根据id查询菜品信息和对应口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.success(dishDto);
    }


    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());

        dishService.updateWithFlavor(dishDto);
        //清理某个分类下的菜品缓存
        String keys = "dish_"+dishDto.getCategoryId()+"_1";
        redisTemplate.delete(keys);

        return R.success("新增菜品成功");
    }

    /**
     * 根据条件查询对应菜品信息
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){

        List<DishDto> dishDtoList =null;

        //动态获取key
        String key = "dish_"+dish.getCategoryId()+"_"+dish.getStatus();

        //设置缓存数据
        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);

        //如果有缓存数据，不用查询数据库
        if (dishDtoList != null){
            return R.success(dishDtoList);
        }


        //构造查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null ,Dish::getCategoryId,dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus,1);

        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            //当前菜品的id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        //如果没有缓存数据，进行数据库查询，并在缓存中添加数据
        redisTemplate.opsForValue().set(key,dishDtoList,60, TimeUnit.MINUTES);

        return R.success(dishDtoList);
    }

    /**
     * 对菜品进行停售或起售
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable("status") Integer status,Long ids){
           log.info("status:{}",status);
           log.info("ids:{}",ids);
        Dish dish = dishService.getById(ids);
        if (dish != null){
            dish.setStatus(status);
            dishService.updateById(dish);
            return R.success("开始起售");
        }
        return R.error("售卖状态设置异常");
    }

}
