package com.czndata.seckill.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "product_stock", autoResultMap = true)
public class ProductStockDO {
    @TableId
    private Long stockId;
    private Integer stock;
    private Long productId;
}
