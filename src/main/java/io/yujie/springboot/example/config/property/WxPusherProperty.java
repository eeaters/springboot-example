package io.yujie.springboot.example.config.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "wx.pusher")
@Configuration
@Data
public class WxPusherProperty {
    private String appToken;
    private Topic topic;

    @Data
    public static class Topic{
        private Long eighty;
    }
}
