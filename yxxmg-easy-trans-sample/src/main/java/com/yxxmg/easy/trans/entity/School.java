package com.yxxmg.easy.trans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.trans.vo.TransPojo;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/4
 */
@TableName("tbl_school")
@Data
public class School implements TransPojo {
    @TableId
    private String schoolId;
    private String schoolName;
    private String remark;
}
