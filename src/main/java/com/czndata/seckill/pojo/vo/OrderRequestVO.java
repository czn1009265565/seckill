package com.czndata.seckill.pojo.vo;

import lombok.Data;

@Data
public class OrderRequestVO {
    private Long userId;
    private Long productId;
    private Long promoId;
    private Integer amount;
}
