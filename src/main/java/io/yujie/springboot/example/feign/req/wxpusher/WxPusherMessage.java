package io.yujie.springboot.example.feign.req.wxpusher;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class WxPusherMessage {
    /**
     * 1:text，可以直接显示在卡片里面
     * 2:html，点击以后查看，支持html
     * 3:md，markdown格式，和html类似
     */
    public static final int CONTENT_TYPE_TEXT = 1;
    public static final int CONTENT_TYPE_HTML = 2;
    public static final int CONTENT_TYPE_MD = 3;

    private String appToken;

    //发送的目标
    private Set<String> uids;
    private Set<Long> topicIds;

    private Integer contentType;

    private String content;

    private String summary;

    /**
     * 是否验证付费
     */
    private boolean verifyPay;

    /**
     * 仅针对text消息类型有效
     */
    private String url;

    public void setTopicId(Long topicId) {
        this.topicIds = new HashSet<>(1);
        this.topicIds.add(topicId);
    }
}
