package com.czndata.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czndata.seckill.pojo.entity.ProductDO;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends BaseMapper<ProductDO> {
    int increaseSales(@Param("productId") Long productId, @Param("amount") Integer amount);
}
