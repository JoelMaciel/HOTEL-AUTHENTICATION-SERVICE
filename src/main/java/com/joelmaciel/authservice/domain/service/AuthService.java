package com.joelmaciel.authservice.domain.service;

import com.joelmaciel.authservice.api.dtos.AuthUserDTO;
import com.joelmaciel.authservice.api.dtos.NewUserDTO;
import com.joelmaciel.authservice.api.dtos.RequestDTO;
import com.joelmaciel.authservice.api.dtos.TokenDTO;
import com.joelmaciel.authservice.domain.entities.AuthUser;

import java.util.Optional;

public interface AuthService {

    AuthUser save(NewUserDTO newUserDTO);

    TokenDTO login(AuthUserDTO authUserDTO);
    TokenDTO validate(String token, RequestDTO requestDTO);


}
