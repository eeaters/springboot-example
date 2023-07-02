package io.yujie.springboot.example.feigh;

import io.yujie.springboot.example.feign.SfDeliveryClient;
import io.yujie.springboot.example.feign.base.SfResult;
import io.yujie.springboot.example.feign.req.sf.SfPreOrderReq;
import io.yujie.springboot.example.feign.res.sf.SfPreOrderResp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@EnableFeignClients(clients = SfDeliveryClient.class)
public class SfClientTest {

    @Test
    public void sendMessage() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(this.getClass(),
                FeignAutoConfiguration.class,
                HttpMessageConvertersAutoConfiguration.class
              );
        context.refresh();

        SfDeliveryClient sfDeliveryClient = context.getBean(SfDeliveryClient.class);
        SfPreOrderReq preOrderReq = new SfPreOrderReq();
        preOrderReq.setDevId(1668137074);
        preOrderReq.setShopId("99999");
        preOrderReq.setShopType(2);
        preOrderReq.setProductType(10);
        preOrderReq.setPushTime(Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))));
        preOrderReq.setWeight(0);
        preOrderReq.setUserLat("40.030613");
        preOrderReq.setUserLng("116.354787");
        preOrderReq.setUserAddress("海淀区清河龙岗路51号清润家园小区 永辉");
        preOrderReq.setReturnFlag(511);
        SfResult<SfPreOrderResp> sfResult = sfDeliveryClient.preCreateOrder(preOrderReq);
        System.out.println("sfResult = " + sfResult);
        context.close();
    }
}
