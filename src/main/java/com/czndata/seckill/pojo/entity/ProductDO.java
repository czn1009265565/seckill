package com.czndata.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "product", autoResultMap = true)
public class ProductDO {
    @TableId
    private Long productId;
    private String title;
    private BigDecimal price;
    private String description;
    private Integer sales;
    private String imgUrl;
}
