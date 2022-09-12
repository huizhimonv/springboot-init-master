package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.jcjc.project.mapper.OrdersMapper;
import com.jcjc.project.model.entity.Orders;
import com.jcjc.project.service.OrdersService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService {

}




