package com.czndata.seckill.service;

import com.czndata.seckill.pojo.entity.PromoDO;

public interface PromoService {

    PromoDO getById(Long promoId);

    void publishPromo(Long promoId);
}
