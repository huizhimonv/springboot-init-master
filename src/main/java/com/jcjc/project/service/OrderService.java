package com.jcjc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcjc.project.model.entity.Orders;


/**
* @author 神原秋人
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-09-07 17:12:32
*/
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void sumbit(Orders orders);

}
