package com.yxxmg.gateway;

import static org.springframework.cloud.gateway.filter.factory.RewriteResponseHeaderGatewayFilterFactory.REGEXP_KEY;
import static org.springframework.cloud.gateway.filter.factory.RewriteResponseHeaderGatewayFilterFactory.REPLACEMENT_KEY;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory.PATTERN_KEY;
import static org.springframework.cloud.gateway.support.NameUtils.normalizeFilterFactoryName;
import static org.springframework.cloud.gateway.support.NameUtils.normalizeRoutePredicateName;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClientAutoConfiguration;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

import com.google.common.collect.Lists;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/7
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
@AutoConfigureBefore(GatewayAutoConfiguration.class)
@AutoConfigureAfter(CompositeDiscoveryClientAutoConfiguration.class)
@ConditionalOnClass(DispatcherHandler.class)
@EnableConfigurationProperties
public class GatewayDiscoveryClientAutoConfiguration {
    // 1.初始化断言
    public static List<PredicateDefinition> initPredicates() {
        List<PredicateDefinition> definitions = Lists.newArrayList();
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName(normalizeRoutePredicateName(PathRoutePredicateFactory.class));
        predicateDefinition.addArg(PATTERN_KEY, "'/' +serviceId+'/**'");
        definitions.add(predicateDefinition);
        return definitions;
    }

    // 2.初始化过滤器
    public static List<FilterDefinition> initFilters() {
        List<FilterDefinition> definitions = Lists.newArrayList();
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName(normalizeFilterFactoryName(RewritePathGatewayFilterFactory.class));
        String regex = "'/' + serviceId + '/(?<remaining>.*)'";
        String replacement = "'/${remaining}'";
        filterDefinition.addArg(REGEXP_KEY, regex);
        filterDefinition.addArg(REPLACEMENT_KEY, replacement);
        definitions.add(filterDefinition);
        return definitions;
    }

    @Bean
    public DiscoveryLocatorProperties discoveryLocatorProperties() {
        // 3.加载应用中关于注册中心路由的配置信息
        DiscoveryLocatorProperties properties = new DiscoveryLocatorProperties();
        // 4.加载断言信息
        properties.setPredicates(initPredicates());
        // 5.加载过滤器信息
        properties.setFilters(initFilters());
        return properties;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(value = "spring.cloud.gateway.discovery.reactive.enabled", matchIfMissing = true)
    public static class ReactiveDiscoveryClientRouteDefinitionLocatorConfiguration {
        @Bean
        @ConditionalOnProperty(name = "spring.cloud.gateway.discovery.locator.enabled")
        public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
            ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
            return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
        }
    }
}
