package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcjc.project.common.BaseContext;
import com.jcjc.project.common.R;
import com.jcjc.project.mapper.ShoppingCartMapper;


import com.jcjc.project.model.entity.ShoppingCart;
import com.jcjc.project.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
* @author 神原秋人
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public R<String> clean() {
            //SQL delete from shopping_cart where user_Id = ?
            LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

            shoppingCartService.remove(queryWrapper);

            return R.success("清空购物车成功");
        }
}





