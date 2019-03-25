package com.wechat.timer.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName WechatBaseConfig
 * @Author minghangliu
 * @Date 2019/3/25 10:44 PM
 * @Version v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatBaseConfig {

    private String appid;

    private String secret;
}
