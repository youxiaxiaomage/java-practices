package com.yxxmg.gof.test.behavior.visitor;

import org.junit.Test;

import com.yxxmg.gof.behavior.visitor.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/12
 */
public class ClientTest {
    @Test
    public void test() {
        MaterialSet set = new MaterialSet();
        set.add(new Paper());
        set.add(new Copper());
        Company artCompany = new ArtCompany();
        System.out.println(set.accept(artCompany));
        System.out.println("==========================");
        Company mintCompany = new MintCompany();
        System.out.println(set.accept(mintCompany));
    }
}
