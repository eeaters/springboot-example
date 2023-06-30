package io.yujie.springboot.example.enums;

import io.yujie.springboot.example.expand.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum ResultCodeEnum implements CodeEnum {

    SUCCESS(0, "OK"),

    PARAM_ERROR(401, "参数校验异常"),

    LOGIC_ERROR(402, "逻辑处理异常"),

    SYSTEM_ERROR(500, "系统异常"),
    ;
    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
