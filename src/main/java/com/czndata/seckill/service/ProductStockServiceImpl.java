package com.czndata.seckill.service;

import com.czndata.seckill.dao.ProductStockMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class ProductStockServiceImpl implements ProductStockService{

    @Resource
    private ProductStockMapper productStockMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public long decreaseStock(Long productId, Integer amount) {
        String key = "promo:stock:" + productId;
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/decrease.lua")));
        // 指定返回类型
        redisScript.setResultType(Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        return (long) redisTemplate.execute(redisScript, Collections.singletonList(key), amount);
    }

    @Override
    public long decreaseStockReal(Long productId, Integer amount) {
        return productStockMapper.decreaseStock(productId, amount);
    }
}
