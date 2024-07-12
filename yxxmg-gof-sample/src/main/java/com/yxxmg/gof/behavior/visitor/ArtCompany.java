package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/12
 */
public class ArtCompany implements Company {
    @Override
    public String create(Paper paper) {
        return "打印广告";
    }

    @Override
    public String create(Copper copper) {
        return "孔子铜像";
    }
}
