package io.yujie.springboot.example.enums;

import lombok.Getter;

@Getter
public enum DeliveryChannelEnum {

    SF("shunfeng"),
    MT("meituan"),
    FN("fengniao"),

    ;

    private String channelName;

    DeliveryChannelEnum(String channelName) {
        this.channelName = channelName;
    }

}
