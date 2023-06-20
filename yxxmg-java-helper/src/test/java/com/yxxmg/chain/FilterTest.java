package com.yxxmg.chain;

import com.google.common.collect.Lists;
import junit.framework.TestCase;

import java.util.Comparator;
import java.util.List;

/**
 * 责任链+排序
 */
public class FilterTest extends TestCase {
    public void testPriority() {
        List<Filter> filterList = Lists.newArrayList();
        RequestLimitFilter requestLimitFilter = new RequestLimitFilter();
        RequestParamFilter requestParamFilter = new RequestParamFilter();
        ResponseFilter responseFilter = new ResponseFilter();

        filterList.add(requestLimitFilter);
        filterList.add(requestParamFilter);
        filterList.add(responseFilter);
        filterList.forEach(filter -> System.out.println(filter.priority() + filter.doFilter()));
        filterList.sort(Comparator.comparingInt(Filter::priority));
        filterList.forEach(filter -> System.out.println(filter.priority() + filter.doFilter()));
        assertEquals(Integer.MIN_VALUE, filterList.get(0).priority());
        assertEquals(200, filterList.get(1).priority());
        assertEquals(Integer.MAX_VALUE, filterList.get(2).priority());
    }

}