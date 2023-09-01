package com.yxxmg.plugin;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 插件配置
 * @since : 2023/8/11
 */
@Data
public class PluginConfig implements Serializable {
    private static final long serialVersionUID = -1995893130937029549L;
    private String id;
    private String jarPath;
    private String jarName;
    private List<PluginItfConfig> pluginItfConfigLIst;
}
