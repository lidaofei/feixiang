package com.feixiang.demo.spi.config;

import com.feixiang.demo.spi.annotation.EnableAutoDemoConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/*
 * @author lidaofei
 * @date 2019/11/29 18:30
 */
public class AutoConfigurationImportSelectorDemo implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<String> configutions = SpringFactoriesLoader.loadFactoryNames(EnableAutoDemoConfig.class, null);
        return StringUtils.toStringArray(configutions);
    }
}
