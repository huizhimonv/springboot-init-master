package com.jcjc.project.dto;

import com.jcjc.project.model.entity.OrderDetail;
import com.jcjc.project.model.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author 神原秋人
 */
@Data
public class OrderDto extends Orders {

    private List<OrderDetail> orderDetails;
}
