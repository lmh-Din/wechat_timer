package com.wechat.timer.entity;

import com.wechat.timer.enums.MsgType;
import lombok.Data;

/**
 * @Description
 * @ClassName TextMsg
 * @Author Liumh
 * @Date 2019/4/17 10:07
 * @Version v1.0
 */
@Data
public class TextMsg {

    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private Integer createTime;

    /**
     * 消息类型，文本为text
     */
    private MsgType msgType;

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 消息id，64位整型
     */
    private Integer msgId;
}
