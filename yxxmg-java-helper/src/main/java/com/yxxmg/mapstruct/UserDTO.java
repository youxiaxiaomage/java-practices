package com.yxxmg.mapstruct;

import java.io.Serializable;
import java.util.List;

import com.yxxmg.mapstruct.convert.UserDTOMapper;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1489990626765390116L;
    private String userId;
    private String userName;
    private String gender;
    private String status;

    public static List<UserDTO> convert(List<User> userList) {
        return UserDTOMapper.MAPPER.convert(userList);
    }

}
