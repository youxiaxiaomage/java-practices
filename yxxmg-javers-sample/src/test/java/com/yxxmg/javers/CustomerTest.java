package com.yxxmg.javers;

import org.javers.core.Changes;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class CustomerTest {
    @Test
    public void test() {
        List<CustomerLabel> customerLabelList = Stream.of(new CustomerLabel().setId("l1").setLabelName("成交客户"),
            new CustomerLabel().setId("l2").setLabelName("目标客户")).collect(Collectors.toList());
        Dealer dealer1 = new Dealer().setDealerId("d1").setDearName("上汽通用");
        Customer customer1 = new Customer().setPkCustomer("123456").setCustomerType(CustomerTypeEnum.DONE)
            .setDealer(dealer1).setLabels(customerLabelList);

        List<CustomerLabel> customerLabelList2 =
            Collections.singletonList(new CustomerLabel().setId("l13").setLabelName("潜在客户"));
        Dealer dealer2 = new Dealer().setDealerId("d2").setDearName("上汽五菱");
        Customer customer2 = new Customer().setPkCustomer("123456").setCustomerType(CustomerTypeEnum.TARGET)
            .setDealer(dealer2).setLabels(customerLabelList2);
        Changes changes = JaversUtil.diff(customer1, customer2);
    }

    @Test
    public void test2() {
        List<CustomerLabel> customerLabelList = Stream.of(new CustomerLabel().setId("l1").setLabelName("成交客户"),
            new CustomerLabel().setId("l2").setLabelName("目标客户")).collect(Collectors.toList());

        List<CustomerLabel> customerLabelList2 =
            Collections.singletonList(new CustomerLabel().setId("l13").setLabelName("潜在客户"));
        Changes changes = JaversUtil.diff(customerLabelList, customerLabelList2, CustomerLabel.class);
        for (Change change : changes) {
            System.out.println(change);
        }
    }
}