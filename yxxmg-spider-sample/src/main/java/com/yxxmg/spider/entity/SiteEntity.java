package com.yxxmg.spider.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
@Data
@Builder
@TableName("TBL_SITE")
public class SiteEntity implements Serializable {
    private static final long serialVersionUID = 9102001178327049141L;
    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;
    @TableField("url")
    private String url;
    @TableField("title")
    private String title;
    @TableField("content")
    private String content;
    @TableField("release_time")
    private String releaseTime;
}
