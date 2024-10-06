package com.czndata.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "order_info", autoResultMap = true)
public class OrderInfoDO {
    @TableId
    private Long orderId;
    private Long userId;
    private Long productId;
    private Long promoId;
    private BigDecimal productPrice;
    private Integer amount;
    private BigDecimal orderPrice;
}
