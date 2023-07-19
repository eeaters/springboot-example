package io.yujie.springboot.example.handler;

import io.yujie.springboot.example.config.exception.LogicException;
import io.yujie.springboot.example.config.property.SfDeliveryProperty;
import io.yujie.springboot.example.entity.vo.req.PreOrderReq;
import io.yujie.springboot.example.entity.vo.resp.PreOrderResp;
import io.yujie.springboot.example.enums.DeliveryChannelEnum;
import io.yujie.springboot.example.feign.SfDeliveryClient;
import io.yujie.springboot.example.feign.base.SfResult;
import io.yujie.springboot.example.feign.req.sf.SfPreOrderReq;
import io.yujie.springboot.example.feign.res.sf.SfPreOrderResp;
import io.yujie.springboot.example.mock.feign.SFMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SFDeliveryHandler implements DeliveryHandler{

    @Autowired
    SfDeliveryClient sfDeliveryClient;

    @Autowired
    SfDeliveryProperty deliveryProperty;


    @Override
    public PreOrderResp preOrder(PreOrderReq orderReq) {
        SfPreOrderReq req = SfPreOrderReq.getInstance(orderReq, deliveryProperty);
        //给个开关控制是否调用下游
        if (deliveryProperty.getMock()) {
            return SFMock.mockPreOrder();
        }
        SfResult<SfPreOrderResp> sfResult = sfDeliveryClient.preCreateOrder(req);
        if (sfResult.getErrorCode() != 0) {
            throw new LogicException(sfResult.getErrorMsg());
        }
        PreOrderResp resp = new PreOrderResp();
        resp.setChannel(DeliveryChannelEnum.SF.getChannelName());
        resp.setExceptTime(sfResult.getResult().getExpectTime());
        resp.setTotalPrice(sfResult.getResult().getTotalPrice());
        return resp;
    }
}
