package com.yxxmg.javers;

import com.yxxmg.javers.annotation.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 客户对象
 * @since : 2023/6/9
 */
@Data
@Accessors(chain = true)
public class Customer implements Serializable {
    private static final long serialVersionUID = -2674757127945649969L;
    @Id
    private String pkCustomer;
    @Column(name = "客户类型")
    private CustomerTypeEnum customerType;
    @Column(name = "客户标签", isCollection = true)
    private List<CustomerLabel> labels;
    @Column(name = "经销商")
    private Dealer dealer;
}
