package com.czndata.seckill.listener;

import cn.hutool.json.JSONUtil;
import com.czndata.seckill.pojo.constants.KafkaConstants;
import com.czndata.seckill.pojo.vo.OrderRequestVO;
import com.czndata.seckill.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @Resource
    private OrderService orderService;

    @KafkaListener(topics = KafkaConstants.TOPIC_NAME, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message);
            OrderRequestVO bean = JSONUtil.toBean(message, OrderRequestVO.class);
            orderService.asynCreateOrder(bean);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
