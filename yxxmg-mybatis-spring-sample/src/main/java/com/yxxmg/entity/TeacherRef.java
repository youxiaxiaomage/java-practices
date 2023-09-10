package com.yxxmg.entity;

import java.io.Serializable;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
@Data
public class TeacherRef implements Serializable {
    private static final long serialVersionUID = -5394705106965978428L;
    private String teacherId;
    private String teacherName;

    public static TeacherRef convert(Teacher teacher) {
        return TeacherRefMapper.MAPPER.to(teacher);
    }

    @Mapper
    interface TeacherRefMapper {
        TeacherRefMapper MAPPER = Mappers.getMapper(TeacherRefMapper.class);

        TeacherRef to(Teacher teacher);
    }
}
