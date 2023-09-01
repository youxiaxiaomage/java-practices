package com.yxxmg.plugin;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 插件对象
 * @since : 2023/8/11
 */
@Data
@AllArgsConstructor
public class Plugin implements Serializable {
    private static final long serialVersionUID = -8225588241045876005L;
    private PluginConfig pluginConfig;
    private CustomClassLoader customClassLoader;
    private Map<String, PluginItf> path2Method;

    public static Plugin to(PluginConfig pluginConfig) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        customClassLoader.loadJar(pluginConfig.getJarPath());
        Map<String, PluginItf> itf2Method = filterPluginItfMap(pluginConfig, customClassLoader);
        if (MapUtils.isEmpty(itf2Method)) {
            throw new IllegalStateException("插件未定义接口");
        }
        return new Plugin(pluginConfig, customClassLoader, itf2Method);
    }

    public static Map<String, PluginItf> filterPluginItfMap(PluginConfig pluginConfig,
        CustomClassLoader customClassLoader) {
        Map<String, PluginItf> map = Maps.newHashMap();
        for (PluginItfConfig pluginItfConfig : pluginConfig.getPluginItfConfigLIst()) {
            try {
                PluginItf pluginItf = generatePluginItf(pluginConfig, customClassLoader, pluginItfConfig);
                map.put(pluginItfConfig.getPath(), pluginItf);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
        return map;
    }

    private static PluginItf generatePluginItf(PluginConfig pluginConfig, CustomClassLoader customClassLoader,
        PluginItfConfig pluginItfConfig) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(pluginItfConfig.getFullClazzName(), false, customClassLoader);
        Object obj = clazz.newInstance();
        Method method = getMethod(clazz.getDeclaredMethods(), "close");
        return new PluginItf().setClazz(clazz).setObject(obj).setMethod(method).setItfName(pluginItfConfig.getItfName())
            .setPluginConfig(pluginConfig).setCloseMethod(method);
    }

    private static Method getMethod(Method[] declaredMethods, String methodName) {
        if (ObjectUtils.isEmpty(declaredMethods)) {
            return null;
        }
        return Arrays.stream(declaredMethods).filter(method -> StringUtils.equals(method.getName(), methodName))
            .findFirst().orElse(null);
    }
}
