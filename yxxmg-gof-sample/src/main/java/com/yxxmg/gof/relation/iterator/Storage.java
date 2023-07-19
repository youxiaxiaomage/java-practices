package com.yxxmg.gof.relation.iterator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/11
 */
public class Storage implements Iterator {
    Object[] elementData;
    int position = 0;

    public Storage(Object[] elementData) {
        this.elementData = elementData;
    }

    @Override
    public boolean hasNext() {
        return position <= elementData.length - 1 && elementData[position] != null;
    }

    @Override
    public Object next() {
        return elementData[position++];
    }
}
