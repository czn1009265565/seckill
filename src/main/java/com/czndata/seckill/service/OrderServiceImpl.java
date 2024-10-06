package com.czndata.seckill.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.czndata.seckill.dao.*;
import com.czndata.seckill.pojo.constants.KafkaConstants;
import com.czndata.seckill.pojo.entity.*;
import com.czndata.seckill.pojo.vo.OrderRequestVO;
import com.czndata.seckill.pojo.vo.OrderResponseVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductService productService;
    @Resource
    private ProductStockService productStockService;
    @Resource
    private PromoService promoService;
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Transactional
    public OrderResponseVO createOrder(OrderRequestVO orderRequestVO) {
        // 参数校验
        check(orderRequestVO);
        // 减库存
        Long count = productStockService.decreaseStock(orderRequestVO.getProductId(), orderRequestVO.getAmount());
        Assert.isTrue(count > 0, "库存扣减失败!");
        // 发送消息
        kafkaTemplate.send(KafkaConstants.TOPIC_NAME, JSONUtil.toJsonStr(orderRequestVO));
        return new OrderResponseVO();
    }

    private void check(OrderRequestVO orderRequestVO) {
        // 参数校验
        // 1.商品是否存在 2.用户是否存在 3.秒杀活动是否存在 4.购买数量是否正确
        Long productId = orderRequestVO.getProductId();
        ProductDO productDO = productService.getById(productId);
        Assert.notNull(productDO, "商品信息不存在!");

        Long userId = orderRequestVO.getUserId();
        UserInfoDO userInfoDO = userInfoMapper.selectById(userId);
        Assert.notNull(userInfoDO, "用户不存在!");

        Long promoId = orderRequestVO.getPromoId();
        PromoDO promoDO = promoService.getById(promoId);
        Assert.notNull(promoDO, "秒杀活动不存在!");

        Integer amount = orderRequestVO.getAmount();
        Assert.isTrue(Objects.nonNull(amount) && amount>0 && amount<99, "购买数量不合法!");
    }

    @Override
    @Transactional
    public void asynCreateOrder(OrderRequestVO orderRequestVO) {
        Long productId = orderRequestVO.getProductId();
        Long userId = orderRequestVO.getUserId();
        Long promoId = orderRequestVO.getPromoId();
        Integer amount = orderRequestVO.getAmount();
        PromoDO promoDO = promoService.getById(promoId);
        // 扣减库存
        long count = productStockService.decreaseStockReal(productId, amount);
        Assert.isTrue(count > 0, "库存扣减失败!");
        // 保存订单
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoDO.setOrderId(IdUtil.getSnowflakeNextId());
        orderInfoDO.setUserId(userId);
        orderInfoDO.setProductId(productId);
        orderInfoDO.setPromoId(promoId);
        orderInfoDO.setProductPrice(promoDO.getPromoProductPrice());
        orderInfoDO.setAmount(amount);
        orderInfoDO.setOrderPrice(promoDO.getPromoProductPrice().multiply(new BigDecimal(amount)));
        orderInfoMapper.insert(orderInfoDO);

        // 增加商品销量
        productMapper.increaseSales(productId, amount);
    }
}
