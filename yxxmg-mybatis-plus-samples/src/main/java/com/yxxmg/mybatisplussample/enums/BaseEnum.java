package com.yxxmg.mybatisplussample.enums;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 枚举基类
 * @since : 2022/11/3
 */
public interface BaseEnum {
    /**
     * 获取枚举类
     *
     * @param type 枚举类
     * @param value 数据库中存储值
     * @param <T> 枚举类
     * @return 转换后的自定义枚举类
     */
    static <T extends BaseEnum> T getEnum(Class<T> type, Integer value) {
        T[] enumConstants = type.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getCode().equals(value)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 编码
     *
     * @return int
     */
    Integer getCode();

    /**
     * 描述
     *
     * @return 编码
     */
    String getDesc();

    /**
     * 枚举类列表
     * 
     * @param type 枚举类
     * @return List<BaseEnum>
     */
    static List<BaseEnum> getAllEnums(Class<?> type) {
        if (!BaseEnum.class.isAssignableFrom(type)) {
            return Collections.emptyList();
        }
        Object[] enumConstants = type.getEnumConstants();
        return Stream.of(enumConstants).map(obj -> (BaseEnum)obj).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
