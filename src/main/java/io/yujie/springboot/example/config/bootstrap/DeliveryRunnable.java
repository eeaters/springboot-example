package io.yujie.springboot.example.config.bootstrap;

import io.yujie.springboot.example.entity.vo.req.PreOrderReq;
import io.yujie.springboot.example.entity.vo.resp.PreOrderResp;
import io.yujie.springboot.example.enums.DeliveryChannelEnum;
import io.yujie.springboot.example.handler.DeliveryHandlerProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class DeliveryRunnable {

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            PreOrderReq preOrderReq = new PreOrderReq();
            preOrderReq.setStoreCode("99999");
            preOrderReq.setWeightGram(1);
            preOrderReq.setReceiveUserAddress("北京市海淀区学清嘉创大厦A座15层");
            preOrderReq.setReceiveUserLongitude("116.354787");
            preOrderReq.setReceiveUserLatitude("40.030613");
            PreOrderResp resp = DeliveryHandlerProvider.getInstance(DeliveryChannelEnum.SF)
                    .preOrder(preOrderReq);
            System.out.println("resp = " + resp);
        };
    }
}
