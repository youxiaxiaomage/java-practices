package com.yxxmg.gof.create.factory.impl;

import com.yxxmg.gof.create.builder.entity.Teacher;
import com.yxxmg.gof.create.factory.IDao;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/5
 */
public class TeacherDaoImpl implements IDao<Teacher> {
    @Override
    public boolean save(Teacher o) {
        System.out.println("TeacherDaoImpl.save");
        return false;
    }

    @Override
    public int update(Teacher o) {
        System.out.println("TeacherDaoImpl.update");
        return 0;
    }

    @Override
    public boolean delete(Teacher o) {
        System.out.println("TeacherDaoImpl.delete");
        return false;
    }

    @Override
    public Teacher selectById(String id) {
        System.out.println("TeacherDaoImpl.selectById");
        return null;
    }
}
