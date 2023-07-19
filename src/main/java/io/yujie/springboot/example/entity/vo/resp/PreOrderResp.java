package io.yujie.springboot.example.entity.vo.resp;

import lombok.Data;

@Data
public class PreOrderResp {

    private String channel;

    private Integer totalPrice;

    private Integer exceptTime;
}
