package com.czndata.seckill.controller;

import com.czndata.seckill.pojo.vo.ResponseVO;
import com.czndata.seckill.service.PromoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "活动管理")
@RestController
@RequestMapping(value = "/promo")
public class PromoController {

    @Resource
    private PromoService promoService;

    @Operation(summary = "发布活动")
    @GetMapping(value = "/publishPromo")
    public ResponseVO publishPromo(@RequestParam("promoId") Long promoId) {
        promoService.publishPromo(promoId);
        return ResponseVO.success();
    }
}
