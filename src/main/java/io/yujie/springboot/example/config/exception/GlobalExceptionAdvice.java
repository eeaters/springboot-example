package io.yujie.springboot.example.config.exception;


import io.yujie.springboot.example.entity.base.BaseResponse;
import io.yujie.springboot.example.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(AbstractSystemException.class)
    public BaseResponse<String> handleException(AbstractSystemException exception) {
        log.info("业务异常, 异常信息: {}", ExceptionUtils.getStackTrace(exception));
        return BaseResponse.failure(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> handleException(MethodArgumentNotValidException exception) {
        log.warn("校验异常, 异常信息: {}", ExceptionUtils.getStackTrace(exception));
        StringBuilder sb = new StringBuilder();
        exception.getBindingResult()
                .getAllErrors()
                .iterator()
                .forEachRemaining(objectError -> {
                    if (FieldError.class.isAssignableFrom(objectError.getClass())) {
                        FieldError fieldError = (FieldError) objectError;
                        sb.append(fieldError.getField())
                                .append(" : ")
                                .append(objectError.getDefaultMessage())
                                .append(" | ");
                    } else {
                        sb.append(objectError.getDefaultMessage())
                                .append(" | ");
                    }
                });
        String message = sb.substring(0, sb.length() - 1);
        return BaseResponse.failure(ResultCodeEnum.PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception exception) {
        log.error("系統异常, 异常信息: {}", ExceptionUtils.getStackTrace(exception));
        return BaseResponse.failure(ResultCodeEnum.SYSTEM_ERROR);
    }
}
