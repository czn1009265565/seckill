package com.czndata.seckill.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseVO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private Long promoId;
    private BigDecimal productPrice;
    private Integer amount;
    private BigDecimal orderPrice;
}
