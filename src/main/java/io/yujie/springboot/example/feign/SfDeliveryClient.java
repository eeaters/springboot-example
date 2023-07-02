package io.yujie.springboot.example.feign;

import io.yujie.springboot.example.feign.base.SfResult;
import io.yujie.springboot.example.feign.config.SfConfig;
import io.yujie.springboot.example.feign.req.sf.SfPreOrderReq;
import io.yujie.springboot.example.feign.res.sf.SfPreOrderResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        contextId = "third-part-sf",
        name = "third-part-sf",
        url = "https://openic.sf-express.com",
        configuration = SfConfig.class,
        path = "/open/api/external"
)
public interface SfDeliveryClient {

    @PostMapping("/precreateorder")
    SfResult<SfPreOrderResp> preCreateOrder(@RequestBody SfPreOrderReq preOrderReq);

}
