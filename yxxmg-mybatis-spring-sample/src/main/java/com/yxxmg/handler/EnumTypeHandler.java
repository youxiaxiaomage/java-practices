package com.yxxmg.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.yxxmg.enums.Gender;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义枚举转换器
 * @since : 2023/9/9
 */
@MappedTypes(Gender.class)
public class EnumTypeHandler extends BaseTypeHandler<Gender> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    @Override
    public Gender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Gender.of(rs.getInt(columnName));
    }

    @Override
    public Gender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Gender.of(rs.getInt(columnIndex));
    }

    @Override
    public Gender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Gender.of(cs.getInt(columnIndex));
    }
}
