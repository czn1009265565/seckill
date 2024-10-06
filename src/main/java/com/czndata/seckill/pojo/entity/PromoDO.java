package com.czndata.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "promo", autoResultMap = true)
public class PromoDO {
    @TableId
    private Long promoId;
    private String promoName;
    private Long productId;
    private BigDecimal promoProductPrice;
    private Date startDate;
    private Date endDate;
}
