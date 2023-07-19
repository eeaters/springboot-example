package io.yujie.springboot.example.entity.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(name = "预下单的")
public class PreOrderReq {

    @Schema(name = "门店号")
    @NotBlank(message = "门店号不能为空")
    private String storeCode;

    @Schema(name = "用户收货地址")
    private String receiveUserAddress;

    @Schema(name = "收货人经度")
    private String receiveUserLongitude;

    @Schema(name = "收货人纬度")
    private String receiveUserLatitude;

    @Schema(name = "货物重量")
    private Integer weightGram;

}
