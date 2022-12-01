package com.yxxmg.mybatisplussample.dto;

import com.yxxmg.mybatisplussample.domain.User;
import com.yxxmg.mybatisplussample.enums.GenderEnum;
import com.yxxmg.mybatisplussample.validator.AddGroup;
import com.yxxmg.mybatisplussample.validator.UpdateGroup;
import com.yxxmg.mybatisplussample.validator.ValidUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 用户请求对象
 * @since : 2022/11/3
 */
@Data
@ApiModel("用户请求对象")
@ValidUser(groups = {AddGroup.class, UpdateGroup.class})
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 6715147416524554153L;
    @ApiModelProperty(value = "用户主键", required = true)
    @NotNull(message = "用户主键不能为空", groups = UpdateGroup.class)
    @NotBlank(message = "用户名不能为空", groups = UpdateGroup.class)
    private String userId;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    @NotNull(message = "密码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "性别不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "性别", required = true)
    private GenderEnum gender;

    public static User of(UserDTO userDTO) {
        return UserMapper.MAPPER.from(userDTO);
    }

    @Mapper
    interface UserMapper {
        UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

        /**
         * DTO转DO
         * 
         * @param userDTO DTO
         * @return DO
         */
        User from(UserDTO userDTO);
    }
}
