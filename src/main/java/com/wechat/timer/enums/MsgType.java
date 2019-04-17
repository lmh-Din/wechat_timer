package com.wechat.timer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 微信公众号接收用户消息类型
 */
@AllArgsConstructor
@Getter
@ToString
public enum MsgType {

    TEXT("文本", "text"),
    IMAGE("图片", "image"),
    VOICE("语音", "voice"),
    VIDEO("视频", "video"),
    SHORTVIDEO("小视频", "shortvideo");

    private String key;
    private String value;
}
