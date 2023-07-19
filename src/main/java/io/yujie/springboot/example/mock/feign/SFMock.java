package io.yujie.springboot.example.mock.feign;

import io.yujie.springboot.example.entity.vo.resp.PreOrderResp;
import io.yujie.springboot.example.enums.DeliveryChannelEnum;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface SFMock {

    static PreOrderResp mockPreOrder() {
        PreOrderResp preOrderResp = new PreOrderResp();
        preOrderResp.setTotalPrice(100);
        preOrderResp.setChannel(DeliveryChannelEnum.SF.getChannelName());
        preOrderResp.setExceptTime((int) LocalDateTime.now().plusHours(2).toEpochSecond(ZoneOffset.MAX));
        return preOrderResp;
    }
}
