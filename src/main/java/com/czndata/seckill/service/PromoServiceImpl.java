package com.czndata.seckill.service;

import com.czndata.seckill.dao.ProductMapper;
import com.czndata.seckill.dao.ProductStockMapper;
import com.czndata.seckill.dao.PromoMapper;
import com.czndata.seckill.pojo.entity.ProductDO;
import com.czndata.seckill.pojo.entity.ProductStockDO;
import com.czndata.seckill.pojo.entity.PromoDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Slf4j
@Service
public class PromoServiceImpl implements PromoService{

    @Resource
    private PromoMapper promoMapper;

    @Resource
    private ProductStockMapper productStockMapper;

    @Resource
    private RedisService redisService;

    @Override
    public PromoDO getById(Long promoId) {
        if (Objects.isNull(promoId)) return null;

        String key = "promo:promoId:" + promoId;
        PromoDO promo = (PromoDO) redisService.get(key);
        if (Objects.nonNull(promo)) {
            return promo;
        }
        promo = promoMapper.selectById(promoId);
        if (Objects.isNull(promo)) {
            return promo;
        }
        redisService.set(key, promo, 10 * 60);
        return promo;
    }

    @Override
    public void publishPromo(Long promoId) {
        Assert.notNull(promoId, "活动ID不能为空!");

        PromoDO promo = promoMapper.selectById(promoId);
        Long productId = promo.getProductId();
        ProductStockDO productStockDO = productStockMapper.selectByProductId(productId);
        redisService.set("promo:stock:" + productId, productStockDO.getStock());
    }
}
