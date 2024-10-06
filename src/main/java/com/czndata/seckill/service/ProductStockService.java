package com.czndata.seckill.service;

public interface ProductStockService {

    long decreaseStock(Long productId, Integer amount);

    long decreaseStockReal(Long productId, Integer amount);
}
