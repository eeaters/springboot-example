package io.yujie.springboot.example.config.exception;

import io.yujie.springboot.example.enums.ResultCodeEnum;


public class ParamException extends AbstractSystemException {

    public ParamException(Integer code, String message) {
        super(code, message);
    }

    public ParamException(ResultCodeEnum codeEnum) {
        super(codeEnum);
    }

    public ParamException(String message) {
        this(ResultCodeEnum.LOGIC_ERROR.getCode(), message);
    }

}
