package io.yujie.springboot.example.feign;

import io.yujie.springboot.example.feign.base.WxPusherResult;
import io.yujie.springboot.example.feign.req.WxPusherMessage;
import io.yujie.springboot.example.feign.res.MessageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(contextId = "wx.pusher.client", name = "wxPusher", url = "http://wxpusher.zjiecode.com")
public interface WxPusherClient {

    @PostMapping("/api/send/message")
    WxPusherResult<List<MessageResult>> send(@RequestBody WxPusherMessage message);

}
