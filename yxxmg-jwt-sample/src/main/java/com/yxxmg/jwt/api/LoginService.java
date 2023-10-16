package com.yxxmg.jwt.api;

import com.yxxmg.jwt.dto.LoginDTO;
import com.yxxmg.jwt.vo.TokenVO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/16
 */
public interface LoginService {
    TokenVO login(LoginDTO loginDTO);
}
