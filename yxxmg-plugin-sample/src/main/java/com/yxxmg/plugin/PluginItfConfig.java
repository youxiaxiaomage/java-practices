package com.yxxmg.plugin;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/8/11
 */
@Data
public class PluginItfConfig implements Serializable {
    private static final long serialVersionUID = 467834049225462085L;
    private String itfName;
    private String path;
    private String fullClazzName;
    private String methodName;
}
