package io.yujie.springboot.example.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "third.delivery.sf")
@Configuration
@Data
public class SfDeliveryProperty {

    private Integer appId;

    private String appKey;

    private Integer shopType;

    //商品类型, 公司经营的产品应该是固定的一个值
    private Integer productType;

    private Boolean mock;

}
