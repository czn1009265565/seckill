package com.czndata.seckill.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.czndata.seckill.dao.ProductMapper;
import com.czndata.seckill.dao.ProductStockMapper;
import com.czndata.seckill.dao.PromoMapper;
import com.czndata.seckill.dao.UserInfoMapper;
import com.czndata.seckill.pojo.entity.ProductDO;
import com.czndata.seckill.pojo.entity.ProductStockDO;
import com.czndata.seckill.pojo.entity.PromoDO;
import com.czndata.seckill.pojo.entity.UserInfoDO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class OrderServiceImplTest {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductStockMapper productStockMapper;
    @Resource
    private PromoMapper promoMapper;

    @Test
    void initData() {
        userInfoMapper.delete(new LambdaQueryWrapper<>());
        productMapper.delete(new LambdaQueryWrapper<>());
        productStockMapper.delete(new LambdaQueryWrapper<>());
        promoMapper.delete(new LambdaQueryWrapper<>());

        // 初始化admin用户
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(IdUtil.getSnowflakeNextId());
        userInfoDO.setName("admin");
        userInfoDO.setPassword(DigestUtil.sha256Hex("admin"));
        userInfoDO.setGender(1);
        userInfoDO.setAge(30);
        userInfoDO.setTelphone("17858804264");
        userInfoDO.setRegisterMode("byphone");
        userInfoDO.setThirdPartyId("");
        userInfoMapper.insertOrUpdate(userInfoDO);

        ProductDO productDO = new ProductDO();
        long productId = IdUtil.getSnowflakeNextId();
        productDO.setProductId(productId);
        productDO.setTitle("iPhone");
        productDO.setDescription("iPhone16 Pro");
        productDO.setPrice(new BigDecimal("4999"));
        productDO.setImgUrl("http://www.baidu.com");
        productDO.setSales(0);
        productMapper.insertOrUpdate(productDO);

        ProductStockDO productStockDO = new ProductStockDO();
        productStockDO.setStockId(IdUtil.getSnowflakeNextId());
        productStockDO.setProductId(productId);
        productStockDO.setStock(100);
        productStockMapper.insertOrUpdate(productStockDO);

        PromoDO promoDO = new PromoDO();
        promoDO.setPromoId(IdUtil.getSnowflakeNextId());
        promoDO.setProductId(productId);
        promoDO.setPromoName("双11秒杀大促");
        promoDO.setPromoProductPrice(new BigDecimal("4499"));
        promoDO.setStartDate(new Date());
        promoDO.setEndDate(new Date());
        promoMapper.insertOrUpdate(promoDO);
    }
}