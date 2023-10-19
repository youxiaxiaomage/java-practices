package com.yxxmg.hbase.annotation.mapper;

import java.lang.annotation.*;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import com.yxxmg.hbase.mapper.MapperFactoryBean;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MapperScannerRegistrar.class)
@Repeatable(MapperScans.class)
public @interface MapperScan {
    String[] basePackages() default {};

    String[] value() default {};

    Class<?>[] basePackageClasses() default {};

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;

    Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;

    String lazyInitialization() default "";

    String defaultScope() default AbstractBeanDefinition.SCOPE_DEFAULT;

}
