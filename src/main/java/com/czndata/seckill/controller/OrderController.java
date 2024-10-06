package com.czndata.seckill.controller;

import com.czndata.seckill.pojo.vo.OrderRequestVO;
import com.czndata.seckill.pojo.vo.OrderResponseVO;
import com.czndata.seckill.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Operation(summary = "创建订单")
    @PostMapping("/create")
    public OrderResponseVO createOrder(@RequestBody OrderRequestVO orderRequestVO) {
        return orderService.createOrder(orderRequestVO);
    }
}
