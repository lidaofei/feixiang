package com.feixiang.demo.spi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/*
 * @author lidaofei
 * @date 2019/11/29 17:19
 */
@ConfigurationProperties(prefix = "demo.user")
@Data
public class DemoProperties {
    private String name;
    private Integer age;
    private String email;
}
