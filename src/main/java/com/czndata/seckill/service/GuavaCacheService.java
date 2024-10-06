package com.czndata.seckill.service;

public interface GuavaCacheService {

    void set(String key, Object value);

    Object get(String key);
}
