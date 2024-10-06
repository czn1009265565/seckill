package com.czndata.seckill.service;

import com.czndata.seckill.dao.ProductMapper;
import com.czndata.seckill.pojo.entity.ProductDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    private ProductMapper productMapper;

    @Resource
    private RedisService redisService;

    @Override
    public ProductDO getById(Long productId) {
        if (Objects.isNull(productId)) return null;
        String key = "product:productId:" + productId;
        ProductDO product = (ProductDO) redisService.get(key);

        if (Objects.nonNull(product)) {
            return product;
        }
        product = productMapper.selectById(productId);
        if (Objects.isNull(product)) {
            return product;
        }
        redisService.set(key, product, 10 * 60);
        return product;
    }
}
