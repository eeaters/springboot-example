package io.yujie.springboot.example.feign.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SfResult <R>{
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("error_msg")
    private String errorMsg;
    @JsonProperty("error_data")
    private Object errorData;
    @JsonProperty("result")
    private R result;
}
