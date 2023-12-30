package com.yxxmg.gof.create.proxy;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/27
 */
public class UserService implements BaseService {
    @Override
    public String selectById(String id) {
        // TODO
        return "success " + id;
    }
}
