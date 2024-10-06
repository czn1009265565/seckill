package com.czndata.seckill.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GuavaCacheServiceImpl implements GuavaCacheService{
    private static Cache<String, Object> commonCache;

    static {
        commonCache = CacheBuilder.newBuilder()
                .initialCapacity(1000) // 初始容量
                .maximumSize(10000L)   // 设定最大容量
                .expireAfterWrite(30L, TimeUnit.MINUTES) // 设定写入过期时间
                .concurrencyLevel(8)  // 设置最大并发写操作线程数
                .recordStats() // 开启缓存执行情况统计
                .build();
    }

    @Override
    public void set(String key, Object value) {
        commonCache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return commonCache.getIfPresent(key);
    }
}
