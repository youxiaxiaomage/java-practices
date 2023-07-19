package com.yxxmg.liteflow.component;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeIteratorComponent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 迭代器
 * @since : 2023/4/20
 */
@Component("s")
public class Scomponent extends NodeIteratorComponent {
    @Override
    public Iterator<?> processIterator() throws Exception {
        List<String> nameList = Stream.of("Jack", "Tom", "Leimei").collect(Collectors.toList());
        return nameList.listIterator();
    }
}
