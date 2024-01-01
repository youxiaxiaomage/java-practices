package com.yxxmg.gof.structure.composite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LeafTest {
    @Test
    public void test() {
        // 创建一个根节点
        Composite root = new Composite();
        root.doSomething();
        // 创建一个树枝构件
        Composite branch = new Composite();
        // 创建一个叶子节点
        Leaf leaf = new Leaf();
        // 建立整体
        root.add(branch);
        branch.add(leaf);
        display(root);
    }

    // 通过递归遍历树
    public static void display(Composite root) {
        for (Component c : root.getChildren()) {
            if (c instanceof Leaf) { // 叶子节点
                c.doSomething();
            } else { // 树枝节点
                display((Composite)c);
            }
        }
    }

}