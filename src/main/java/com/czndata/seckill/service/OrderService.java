package com.czndata.seckill.service;

import com.czndata.seckill.pojo.vo.OrderRequestVO;
import com.czndata.seckill.pojo.vo.OrderResponseVO;

public interface OrderService {
    OrderResponseVO createOrder(OrderRequestVO orderRequestVO);

    void asynCreateOrder(OrderRequestVO orderRequestVO);
}
