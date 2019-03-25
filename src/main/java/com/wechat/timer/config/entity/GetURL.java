package com.wechat.timer.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName GetURL
 * @Author minghangliu
 * @Date 2019/3/25 10:46 PM
 * @Version v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "url.get")
public class GetURL {
    private String accessToken;
}
