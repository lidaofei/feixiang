package com.feixiang.demo.spi.annotation;

import com.feixiang.demo.spi.config.AutoConfigurationImportSelectorDemo;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/*
 * @author lidaofei
 * @date 2019/11/29 18:25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfigurationImportSelectorDemo.class})
public @interface EnableAutoDemoConfig {
}
