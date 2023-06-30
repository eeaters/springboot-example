package io.yujie.springboot.example.feign.base;

import lombok.Data;

@Data
public class WxPusherResult <T>{
    private Integer code;
    private String msg;

    private T data;
}
