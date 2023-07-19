package io.yujie.springboot.example.handler;

import io.yujie.springboot.example.enums.DeliveryChannelEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHandlerProvider implements ApplicationContextAware {

    static ApplicationContext applicationContext;

    public static DeliveryHandler getInstance(DeliveryChannelEnum channelEnum) {
        return switch (channelEnum) {
            case SF -> applicationContext.getBean(SFDeliveryHandler.class);
            default -> throw new IllegalStateException("Unexpected value: " + channelEnum);
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DeliveryHandlerProvider.applicationContext = applicationContext;
    }
}
