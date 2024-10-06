package com.czndata.seckill.pojo.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),
    PARAM_ERROR(1, "参数异常"),
    NOT_FOUND(2, "数据不存在"),
    TIME_OUT(3, "已超时"),
    ;
    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
