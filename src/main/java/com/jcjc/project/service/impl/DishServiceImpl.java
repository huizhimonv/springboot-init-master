package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jcjc.project.mapper.DishMapper;
import com.jcjc.project.model.entity.Dish;

import com.jcjc.project.service.DishService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService {

}




