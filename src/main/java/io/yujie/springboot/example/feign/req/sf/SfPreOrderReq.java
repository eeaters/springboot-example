package io.yujie.springboot.example.feign.req.sf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.yujie.springboot.example.config.property.SfDeliveryProperty;
import io.yujie.springboot.example.entity.vo.req.PreOrderReq;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class SfPreOrderReq {
    @JsonProperty("dev_id")
    private Integer devId;

    @JsonProperty("shop_id")
    private String shopId;

    @JsonProperty("shop_type")
    private Integer shopType;

    @JsonProperty("user_lng")
    private String userLng;

    @JsonProperty("user_lat")
    private String userLat;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("product_type")
    private Integer productType;

    @JsonProperty("push_time")
    private Integer pushTime;

    @JsonProperty("return_flag")
    private Integer returnFlag;

    public static SfPreOrderReq getInstance(PreOrderReq orderReq, SfDeliveryProperty deliveryProperty) {
        SfPreOrderReq sfPreOrderReq = new SfPreOrderReq();
        sfPreOrderReq.setDevId(deliveryProperty.getAppId());
        sfPreOrderReq.setShopId(orderReq.getStoreCode());
        sfPreOrderReq.setShopType(deliveryProperty.getShopType());
        sfPreOrderReq.setUserLng(orderReq.getReceiveUserLongitude());
        sfPreOrderReq.setUserLat(orderReq.getReceiveUserLatitude());
        sfPreOrderReq.setUserAddress(orderReq.getReceiveUserAddress());
        sfPreOrderReq.setWeight(orderReq.getWeightGram());
        sfPreOrderReq.setPushTime(Math.toIntExact(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))));
        sfPreOrderReq.setProductType(deliveryProperty.getProductType());
        return sfPreOrderReq;
    }
}
