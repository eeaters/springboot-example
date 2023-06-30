package io.yujie.springboot.example.feigh;


import io.yujie.springboot.example.config.property.WxPusherProperty;
import io.yujie.springboot.example.feign.WxPusherClient;
import io.yujie.springboot.example.feign.req.WxPusherMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

@EnableFeignClients(clients = WxPusherClient.class)
@Configuration
public class WxPusherClientTest {

    @Test
    public void sendMessage() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(this.getClass(),
                FeignAutoConfiguration.class,
                HttpMessageConvertersAutoConfiguration.class,
                WxPusherProperty.class);
        context.setEnvironment(getEnv());
        context.refresh();

        WxPusherClient client = context.getBean(WxPusherClient.class);
        WxPusherProperty property = context.getBean(WxPusherProperty.class);
        WxPusherMessage message = new WxPusherMessage();
        message.setAppToken(property.getAppToken());
        message.setTopicId(property.getTopic().getEighty());
        message.setContentType(WxPusherMessage.CONTENT_TYPE_MD);
        message.setContent("""
                ## 你好
                > 这是一条测试消息,带有一张表格
                                
                |  第一列  | 第二列 |
                | ---- | ---- |
                |    1  | 2     |
                """);
        client.send(message);
        context.close();
    }


    public ConfigurableEnvironment getEnv() throws IOException {
        ConfigurableEnvironment configurableEnvironment = new StandardEnvironment();
        YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();
        String name = "application";
        ClassPathResource classPathResource = new ClassPathResource("application.yml");
        EncodedResource encodedResource = new EncodedResource(classPathResource);
        List<PropertySource<?>> load = propertySourceLoader.load(name, encodedResource.getResource());

        for (PropertySource<?> propertySource : load) {
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }
        return configurableEnvironment;
    }

}
