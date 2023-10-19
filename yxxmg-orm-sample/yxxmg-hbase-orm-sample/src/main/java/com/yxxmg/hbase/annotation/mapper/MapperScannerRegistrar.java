package com.yxxmg.hbase.annotation.mapper;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.yxxmg.hbase.mapper.MapperFactoryBean;
import com.yxxmg.hbase.mapper.MapperScannerConfigurer;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class MapperScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        // NOP
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs =
            AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));
        if (Objects.nonNull(mapperScanAttrs)) {
            registerBeanDefinitions(importingClassMetadata, mapperScanAttrs, registry,
                generateBaseBeanName(importingClassMetadata, 0));
        }
    }

    void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs,
        BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder =
            BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        beanDefinitionBuilder.addPropertyValue("processPropertyPlaceHolders", true);

        Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            beanDefinitionBuilder.addPropertyValue("annotationClass", annotationClass);
        }
        Class<?> markerInterfaceClass = annoAttrs.getClass("markerInterface");
        if (!Class.class.equals(markerInterfaceClass)) {
            beanDefinitionBuilder.addPropertyValue("markerInterface", markerInterfaceClass);
        }
        Class<? extends BeanNameGenerator> nameGeneratorClass = annoAttrs.getClass("nameGenerator");
        if (!BeanNameGenerator.class.equals(nameGeneratorClass)) {
            beanDefinitionBuilder.addPropertyValue("nameGenerator", nameGeneratorClass);
        }
        Class<? extends MapperFactoryBean> factoryBeanClass = annoAttrs.getClass("factoryBean");
        if (!MapperFactoryBean.class.equals(factoryBeanClass)) {
            beanDefinitionBuilder.addPropertyValue("mapperFactoryBeanClass", factoryBeanClass);
        }
        List<String> basePackages = Lists.newArrayList();
        basePackages.addAll(
            Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll(Arrays.stream(annoAttrs.getStringArray("basePackages")).filter(StringUtils::hasText)
            .collect(Collectors.toList()));
        basePackages.addAll(Arrays.stream(annoAttrs.getClassArray("basePackageClasses")).map(ClassUtils::getPackageName)
            .collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(basePackages)) {
            basePackages.add(getDefaultBasePackage(annoMeta));
        }
        String lazyInitialization = annoAttrs.getString("lazyInitialization");
        if (StringUtils.hasText(lazyInitialization)) {
            beanDefinitionBuilder.addPropertyValue("lazyInitialization", lazyInitialization);
        }
        String defaultScope = annoAttrs.getString("defaultScope");
        if (!AbstractBeanDefinition.SCOPE_DEFAULT.equals(defaultScope)) {
            beanDefinitionBuilder.addPropertyValue("defaultScope", defaultScope);
        }
        beanDefinitionBuilder.addPropertyValue("basePackage",
            StringUtils.collectionToCommaDelimitedString(basePackages));

        beanDefinitionBuilder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        // 注册BeanDefinition
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());

    }

    private String getDefaultBasePackage(AnnotationMetadata annoMeta) {
        return ClassUtils.getPackageName(annoMeta.getClassName());
    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + MapperScannerRegistrar.class.getSimpleName() + "#" + index;
    }

    public static class RepeatingRegistrar extends MapperScannerRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry) {
            AnnotationAttributes mapperScansAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(MapperScans.class.getName()));
            if (Objects.nonNull(mapperScansAttrs)) {
                AnnotationAttributes[] annotations = mapperScansAttrs.getAnnotationArray("value");
                if (!ObjectUtils.isEmpty(annotations)) {
                    for (int i = 0; i < annotations.length; i++) {
                        registerBeanDefinitions(importingClassMetadata, annotations[i], registry,
                            generateBaseBeanName(importingClassMetadata, i));
                    }
                }
            }
            super.registerBeanDefinitions(importingClassMetadata, registry);
        }
    }
}
