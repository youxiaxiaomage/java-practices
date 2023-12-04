package com.yxxmg.springboot.samples.bar;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/2
 */
public class Bartender {

    private String name;

    public Bartender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
