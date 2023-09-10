package com.yxxmg.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.yxxmg.entity.TeacherRef;
import com.yxxmg.service.TeacherService;
import com.yxxmg.utils.SpringBeanUtils;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
@MappedTypes(TeacherRef.class)
public class TeacherTypeHandler extends BaseTypeHandler<TeacherRef> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TeacherRef teacher, JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, teacher == null ? null : teacher.getTeacherId());
    }

    @Override
    public TeacherRef getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String teacherId = rs.getString(columnName);
        return getTeacherBydId(teacherId);
    }

    @Override
    public TeacherRef getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String teacherId = rs.getString(columnIndex);
        return getTeacherBydId(teacherId);
    }

    @Override
    public TeacherRef getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String teacherId = cs.getString(columnIndex);
        return getTeacherBydId(teacherId);
    }

    public static TeacherRef getTeacherBydId(String teacherId) {
        return teacherId == null ? null : SpringBeanUtils.getBean(TeacherService.class) == null ? null
            : TeacherRef.convert(SpringBeanUtils.getBean(TeacherService.class).getTeacherBydId(teacherId));
    }
}
