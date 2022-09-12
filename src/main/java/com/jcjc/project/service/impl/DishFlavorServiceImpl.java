package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcjc.project.mapper.DishFlavorMapper;
import com.jcjc.project.model.entity.DishFlavor;

import com.jcjc.project.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService {

}




