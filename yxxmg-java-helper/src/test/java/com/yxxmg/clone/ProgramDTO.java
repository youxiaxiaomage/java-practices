package com.yxxmg.clone;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/21
 */
@Setter
@Getter
@Accessors(chain = true)
public class ProgramDTO implements Serializable {
    private static final long serialVersionUID = 1735726156104341923L;
    private Long id;
    private String name;
    boolean isFee;
    private List<GuestDTO> guestList;

    public static Program to(ProgramDTO programDTO) {
        return ProgramMapper.MAPPER.to(programDTO);
    }

    public static Program clone(Program program) {
        return ProgramMapper.MAPPER.clone(program);
    }

    @Mapper
    interface ProgramMapper {
        ProgramMapper MAPPER = Mappers.getMapper(ProgramMapper.class);

        Program to(ProgramDTO programDTO);

        Program clone(Program program);
    }

    @Data
    @Accessors(chain = true)
    static class AttributeDTO implements Serializable {
        private static final long serialVersionUID = -6471256165448561537L;
        private Sex sex;
        private Integer age;
        private String mail;
    }

    @Data
    @Accessors(chain = true)
    static class GuestDTO implements Serializable {
        private static final long serialVersionUID = -3657492977636402631L;
        private Long id;
        private String name;
        private AttributeDTO attribute;
    }
}
