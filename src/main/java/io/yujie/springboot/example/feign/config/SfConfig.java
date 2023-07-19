package io.yujie.springboot.example.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import io.yujie.springboot.example.config.exception.LogicException;
import io.yujie.springboot.example.config.property.SfDeliveryProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class SfConfig {


    @Autowired
    SfDeliveryProperty deliveryProperty;

    @Bean
    public Encoder signEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {

        ObjectMapper objectMapper = new ObjectMapper();
        return new SpringEncoder(messageConverters) {
            @Override
            public void encode(Object requestBody, Type bodyType, RequestTemplate request) throws EncodeException {
                String sign;
                try {
                    sign = generateSign(objectMapper.writeValueAsString(requestBody),
                            deliveryProperty.getAppId(), deliveryProperty.getAppKey());
                } catch (Exception e) {
                    throw new LogicException("顺丰同城加签失败");
                }
                log.info("签名: {} ", sign);
                request.query("sign", sign);
                super.encode(requestBody, bodyType, request);
            }
        };
    }



    public static String generateSign(String postData, Integer appId, String appKey) throws Exception {
        String sb = postData +
                "&" + appId + "&" + appKey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(sb.getBytes(StandardCharsets.UTF_8));
        int i;
        StringBuilder buf = new StringBuilder("");
        for (byte b : md5) {
            i = b;
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return Base64.encodeBase64String(buf.toString().getBytes(StandardCharsets.UTF_8));
    }

}
