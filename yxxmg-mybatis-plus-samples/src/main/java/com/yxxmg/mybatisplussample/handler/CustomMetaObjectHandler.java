package com.yxxmg.mybatisplussample.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义自动填充
 * @since : 2022/11/3
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", this::getCurrentDate, Date.class);
        this.strictInsertFill(metaObject, "updateTime", this::getCurrentDate, Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // mp 官方不支持字段不为空覆盖填充 
        metaObject.setValue("updateTime", null);
        this.strictUpdateFill(metaObject, "updateTime", this::getCurrentDate, Date.class);
    }

    private Date getCurrentDate() {
        return new Date();
    }
}
