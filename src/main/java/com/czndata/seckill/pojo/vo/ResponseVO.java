package com.czndata.seckill.pojo.vo;

import com.czndata.seckill.pojo.enums.ResultEnum;
import lombok.Data;

@Data
public class ResponseVO<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseVO<T> error(Integer code, String msg) {
        return new ResponseVO<>(code, msg, null);
    }

    public static <T> ResponseVO<T> error(ResultEnum resultEnum) {
        return new ResponseVO<>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }
}
