package io.yujie.springboot.example.handler;


import io.yujie.springboot.example.entity.vo.req.PreOrderReq;
import io.yujie.springboot.example.entity.vo.resp.PreOrderResp;

public interface DeliveryHandler {

    PreOrderResp preOrder(PreOrderReq orderReq);

}
