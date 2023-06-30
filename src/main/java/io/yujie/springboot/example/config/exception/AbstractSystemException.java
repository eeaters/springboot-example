package io.yujie.springboot.example.config.exception;

import io.yujie.springboot.example.enums.ResultCodeEnum;
import lombok.Getter;

@Getter
public abstract class AbstractSystemException extends RuntimeException {

    private Integer code;

    public AbstractSystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AbstractSystemException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

}
