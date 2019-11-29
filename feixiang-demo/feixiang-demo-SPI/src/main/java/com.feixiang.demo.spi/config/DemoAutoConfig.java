package com.feixiang.demo.spi.config;

import com.feixiang.demo.spi.model.Demo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author lidaofei
 * @date 2019/11/29 18:17
 */
@Configuration
@ConditionalOnClass({Demo.class})
@EnableConfigurationProperties({DemoProperties.class})
public class DemoAutoConfig {

    @Bean
    public Demo getDemo(DemoProperties demoProperties){
        Demo demo = new Demo();
        demo.setName(demoProperties.getName());
        demo.setAge(demoProperties.getAge());
        demo.setEmail(demoProperties.getEmail());
        return demo;
    }
}
