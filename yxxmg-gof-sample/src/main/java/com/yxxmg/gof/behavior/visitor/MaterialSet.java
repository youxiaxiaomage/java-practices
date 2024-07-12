package com.yxxmg.gof.behavior.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO 描述文件功能
 * @since : 2024/7/12
 */
public class MaterialSet {
    private List<Material> list = new ArrayList<>();

    public String accept(Company visitor) {
        Iterator<Material> iterator = list.iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next().accept(visitor));
        }
        return result.toString();
    }

    public void add(Material element) {
        list.add(element);
    }

    public void remove(Material material) {
        list.remove(material);
    }
}
