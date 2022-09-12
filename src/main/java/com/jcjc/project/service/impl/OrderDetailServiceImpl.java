package com.jcjc.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcjc.project.mapper.OrderDetailMapper;
import com.jcjc.project.model.entity.OrderDetail;
import com.jcjc.project.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService {

}




