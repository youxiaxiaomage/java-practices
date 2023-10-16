package com.yxxmg.jwt.service;

import com.yxxmg.jwt.api.LoginService;
import com.yxxmg.jwt.dto.LoginDTO;
import com.yxxmg.jwt.entity.User;
import com.yxxmg.jwt.utils.JwtUtils;
import com.yxxmg.jwt.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    @Override
    public TokenVO login(LoginDTO loginDTO) {
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));


        return TokenVO
                .builder()
                .token(JwtUtils.createAccessToken((User) authentication.getPrincipal()))
                .build();
    }
}
