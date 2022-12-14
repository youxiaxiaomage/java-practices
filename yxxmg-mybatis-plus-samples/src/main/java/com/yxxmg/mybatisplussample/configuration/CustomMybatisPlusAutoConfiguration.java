package com.yxxmg.mybatisplussample.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.*;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/3
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
@MapperScan("com.yxxmg.mybatisplussample.mapper")
public class CustomMybatisPlusAutoConfiguration {

    /**
     * MP ??????
     */
    private final MybatisPlusProperties properties;
    /**
     * MP ????????? ????????????????????????????????????
     */
    private final Interceptor[] interceptors;

    /**
     * ???????????? ????????????????????????
     */
    private final TypeHandler[] typeHandlers;

    private final LanguageDriver[] languageDrivers;

    /**
     * ???????????????
     */
    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    private final List<SqlSessionFactoryBeanCustomizer> sqlSessionFactoryBeanCustomizers;

    private final List<MybatisPlusPropertiesCustomizer> mybatisPlusPropertiesCustomizers;

    private final ApplicationContext applicationContext;

    @Value("${yxxmg.tableSuffix}")
    private String tableSuffix;

    public CustomMybatisPlusAutoConfiguration(MybatisPlusProperties properties,
        ObjectProvider<Interceptor[]> interceptorsProvider, ObjectProvider<TypeHandler[]> typeHandlersProvider,
        ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader,
        ObjectProvider<DatabaseIdProvider> databaseIdProvider,
        ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
        ObjectProvider<List<SqlSessionFactoryBeanCustomizer>> sqlSessionFactoryBeanCustomizers,
        ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
        ApplicationContext applicationContext) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.typeHandlers = typeHandlersProvider.getIfAvailable();
        this.languageDrivers = languageDriversProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
        this.sqlSessionFactoryBeanCustomizers = sqlSessionFactoryBeanCustomizers.getIfAvailable();
        this.mybatisPlusPropertiesCustomizers = mybatisPlusPropertiesCustomizerProvider.getIfAvailable();
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // TODO ?????? MybatisSqlSessionFactoryBean ????????? SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        applyConfiguration(factory);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            factory.setTypeHandlers(this.typeHandlers);
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }
        // TODO ???????????????????????? TransactionFactory
        this.getBeanThen(TransactionFactory.class, factory::setTransactionFactory);

        // TODO ??????????????????????????????(??????????????????????????????mybatis??????,????????????????????????)
        Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
        if (!ObjectUtils.isEmpty(this.languageDrivers)) {
            factory.setScriptingLanguageDrivers(this.languageDrivers);
        }
        Optional.ofNullable(defaultLanguageDriver).ifPresent(factory::setDefaultScriptingLanguageDriver);

        applySqlSessionFactoryBeanCustomizers(factory);

        // TODO ??????????????? NULL
        GlobalConfig globalConfig = this.properties.getGlobalConfig();
        // TODO ???????????????
        this.getBeanThen(MetaObjectHandler.class, globalConfig::setMetaObjectHandler);
        // TODO ?????????????????????
        this.getBeansThen(IKeyGenerator.class, i -> globalConfig.getDbConfig().setKeyGenerators(i));
        // TODO ??????sql?????????
        this.getBeanThen(ISqlInjector.class, globalConfig::setSqlInjector);
        // TODO ??????ID?????????
        this.getBeanThen(IdentifierGenerator.class, globalConfig::setIdentifierGenerator);
        // TODO ?????? GlobalConfig ??? MybatisSqlSessionFactoryBean
        factory.setGlobalConfig(globalConfig);
        return factory.getObject();
    }

    /**
     * ??????spring???????????????????????????bean,??????????????????
     *
     * @param clazz class
     * @param consumer ??????
     * @param <T> ??????
     */
    private <T> void getBeanThen(Class<T> clazz, Consumer<T> consumer) {
        if (this.applicationContext.getBeanNamesForType(clazz, false, false).length > 0) {
            consumer.accept(this.applicationContext.getBean(clazz));
        }
    }

    /**
     * ??????spring???????????????????????????bean,??????????????????
     *
     * @param clazz class
     * @param consumer ??????
     * @param <T> ??????
     */
    private <T> void getBeansThen(Class<T> clazz, Consumer<List<T>> consumer) {
        if (this.applicationContext.getBeanNamesForType(clazz, false, false).length > 0) {
            final Map<String, T> beansOfType = this.applicationContext.getBeansOfType(clazz);
            List<T> clazzList = new ArrayList<>();
            beansOfType.forEach((k, v) -> clazzList.add(v));
            consumer.accept(clazzList);
        }
    }

    // TODO ???????????? MybatisSqlSessionFactoryBean
    private void applyConfiguration(MybatisSqlSessionFactoryBean factory) {
        // TODO ?????? MybatisConfiguration
        MybatisConfiguration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);
    }

    private void applySqlSessionFactoryBeanCustomizers(MybatisSqlSessionFactoryBean factory) {
        if (!CollectionUtils.isEmpty(this.sqlSessionFactoryBeanCustomizers)) {
            for (SqlSessionFactoryBeanCustomizer customizer : this.sqlSessionFactoryBeanCustomizers) {
                customizer.customize(factory);
            }
        }
    }

    private MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> tableName + tableSuffix);
        mybatisPlusInterceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return mybatisPlusInterceptor;
    }

    @PostConstruct
    public void init() throws Exception {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory(dataSource()).getConfiguration();
        MybatisPlusInterceptor mybatisPlusInterceptor = mybatisPlusInterceptor();
        if (!configuration.getInterceptors().contains(mybatisPlusInterceptor)) {
            configuration.addInterceptor(mybatisPlusInterceptor);
        }
    }

}