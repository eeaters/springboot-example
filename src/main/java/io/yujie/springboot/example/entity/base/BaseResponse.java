package io.yujie.springboot.example.entity.base;

import io.yujie.springboot.example.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<R> {

    private String message;

    private Integer code;

    private R result;

    public static <R> BaseResponse<R> success(R result) {
        return new BaseResponse<>(null, ResultCodeEnum.SUCCESS.getCode(), result);
    }

    public static <R> BaseResponse<R> failure(Integer code, String message) {
        return new BaseResponse<>(message, code, null);
    }

    public static <R> BaseResponse<R> failure(ResultCodeEnum resultCodeEnum) {
        return failure(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static <R> BaseResponse<R> failure(String message) {
        return failure(ResultCodeEnum.LOGIC_ERROR.getCode(), message);
    }


}
