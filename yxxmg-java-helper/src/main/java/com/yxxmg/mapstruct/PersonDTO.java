package com.yxxmg.mapstruct;

import java.io.Serializable;
import java.util.Date;

import com.yxxmg.mapstruct.convert.PersonMapper;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/16
 */
@Data
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1962392065741049494L;
    String describe;
    private Long id;
    private String personName;
    private String age;
    private String source;
    private String height;
    private Date updateDate;
    private Date modifyDate;
    private String createDate;

    public static PersonDTO convert(Person person) {
        return PersonMapper.MAPPER.personDoToDTO(person);
    }

    public static PersonDTO convert2(Person person) {
        return PersonMapper.MAPPER.convert2(person);
    }

}