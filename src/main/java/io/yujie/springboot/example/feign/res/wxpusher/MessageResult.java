package io.yujie.springboot.example.feign.res.wxpusher;

import lombok.Data;

@Data
public class MessageResult {
    private String uid;
    private String status;
    private Integer code;
    private Long messageId;
}
