package com.jcjc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcjc.project.common.R;
import com.jcjc.project.dto.DishDto;
import com.jcjc.project.model.entity.Dish;

import java.util.List;


/**
* @author 神原秋人
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-09-07 17:12:32
*/

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);
    //更新菜品信息以及对应口味的信息
    public void updateWithFlavor(DishDto dishDto);

    //根据传过来的id批量或者是单个的删除菜品
    void deleteByIds(List<Long> ids);
}
