package com.yxxmg.javers;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */

import com.yxxmg.javers.annotation.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CustomerLabel implements Serializable {
    private static final long serialVersionUID = 5949946549828330370L;
    @Id
    private String id;
    @Column(name = "标签名称")
    private String labelName;
}
