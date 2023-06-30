package io.yujie.springboot.example.config.exception;

import io.yujie.springboot.example.enums.ResultCodeEnum;

public class LogicException extends AbstractSystemException {

    public LogicException(Integer code, String message) {
        super(code, message);
    }

    public LogicException(ResultCodeEnum codeEnum) {
        super(codeEnum);
    }

    public LogicException(String message) {
        this(ResultCodeEnum.LOGIC_ERROR.getCode(), message);
    }


}
