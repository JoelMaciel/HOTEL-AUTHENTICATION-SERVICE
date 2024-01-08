package com.joelmaciel.authservice.domain.service.impl;

import com.joelmaciel.authservice.api.dtos.AuthUserDTO;
import com.joelmaciel.authservice.api.dtos.NewUserDTO;
import com.joelmaciel.authservice.api.dtos.RequestDTO;
import com.joelmaciel.authservice.api.dtos.TokenDTO;
import com.joelmaciel.authservice.api.security.JwtProvider;
import com.joelmaciel.authservice.domain.entities.AuthUser;
import com.joelmaciel.authservice.domain.repositories.AuthUserRepository;
import com.joelmaciel.authservice.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AuthUser save(NewUserDTO dto){
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(user.isPresent()){
            return null;
        }
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password)
                .role(dto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    @Override
    public TokenDTO login(AuthUserDTO dto){
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(!user.isPresent()){
            return null;
        }
        if(passwordEncoder.matches(dto.getPassword(),user.get().getPassword())){
            return new TokenDTO(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    @Override
    public TokenDTO validate(String token, RequestDTO requestDto){
        if(!jwtProvider.validate(token,requestDto)){
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(userName).isPresent()){
            return null;
        }
        return new TokenDTO(token);
    }
}
