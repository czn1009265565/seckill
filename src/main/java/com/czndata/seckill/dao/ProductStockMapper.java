package com.czndata.seckill.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czndata.seckill.pojo.entity.ProductStockDO;
import org.apache.ibatis.annotations.Param;

public interface ProductStockMapper extends BaseMapper<ProductStockDO> {
    int decreaseStock(@Param("productId") Long productId, @Param("amount") Integer amount);

    default ProductStockDO selectByProductId(Long productId) {
        LambdaQueryWrapper<ProductStockDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ProductStockDO> condition = lambdaQueryWrapper.eq(ProductStockDO::getProductId, productId);
        return this.selectOne(condition);
    }
}
