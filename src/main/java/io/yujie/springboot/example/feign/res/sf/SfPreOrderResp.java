package io.yujie.springboot.example.feign.res.sf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SfPreOrderResp {

    /**
     * 配送费总额，当return_flag中包含1时返回，单位分（值为计算出来此单总价）
     */
    @JsonProperty("total_price")
    private Integer totalPrice;

    /**
     * 预计送达时间，当return_flag中包含16时返回，时间格式为Unix时间戳，注意转换
     */
    @JsonProperty("expect_time")
    private Integer expectTime;

    /**
     * 预计配送时间（单位: 分）
     */
    @JsonProperty("promise_delivery_time")
    private Integer promiseDeliveryTime;

    /**
     * 配送距离，当return_flag中包含2时返回，单位米（值为计算出来实际配送距离）
     */
    @JsonProperty("delivery_distance_meter")
    private Long deliveryDistanceMeter;


}
