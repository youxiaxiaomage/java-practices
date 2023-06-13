package com.yxxmg.javers;

import com.yxxmg.javers.annotation.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
@Data
@Accessors(chain = true)
public class Dealer implements Serializable {
    private static final long serialVersionUID = 2129707754696822037L;
    @Id
    private String dealerId;

    @Column(name = "经销商名称")
    private String dearName;
}
