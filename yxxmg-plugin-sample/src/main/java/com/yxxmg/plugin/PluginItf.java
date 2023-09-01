package com.yxxmg.plugin;

import java.io.Serializable;
import java.lang.reflect.Method;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/8/11
 */
@Data
@Accessors(chain = true)
public class PluginItf implements Serializable {
    private static final long serialVersionUID = -5513620001376228087L;
    private Class<?> clazz;
    private Object object;
    private Method method;
    private String itfName;
    private PluginConfig pluginConfig;
    private Method closeMethod;

}
