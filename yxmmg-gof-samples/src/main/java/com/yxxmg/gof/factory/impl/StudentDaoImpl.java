package com.yxxmg.gof.factory.impl;

import com.yxxmg.gof.factory.IDao;
import com.yxxmg.gof.factory.entity.Student;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/5
 */
public class StudentDaoImpl implements IDao<Student> {
    @Override
    public boolean save(Student o) {
        System.out.println("StudentDaoImpl.save");
        System.out.println(o);
        return true;
    }

    @Override
    public int update(Student o) {
        System.out.println("StudentDaoImpl.update");
        return 0;
    }

    @Override
    public boolean delete(Student o) {
        System.out.println("StudentDaoImpl.delete");
        return false;
    }

    @Override
    public Student selectById(String id) {
        System.out.println("StudentDaoImpl.selectById");
        return null;
    }
}
