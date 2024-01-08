package com.joelmaciel.authservice.api.controllers;

import com.joelmaciel.authservice.api.dtos.AuthUserDTO;
import com.joelmaciel.authservice.api.dtos.NewUserDTO;
import com.joelmaciel.authservice.api.dtos.RequestDTO;
import com.joelmaciel.authservice.api.dtos.TokenDTO;
import com.joelmaciel.authservice.domain.entities.AuthUser;
import com.joelmaciel.authservice.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    public static final String MESSAGE_SUCCESSFULLY = "Account created successfully";
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO authUserDTO) {
        TokenDTO tokenDTO = authService.login(authUserDTO);
        if (authUserDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(@RequestParam String token, @RequestBody RequestDTO requestDTO) {
        TokenDTO tokenDTO = authService.validate(token, requestDTO);
        if (tokenDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody NewUserDTO newUserDTO) {
        AuthUser authUser = authService.save(newUserDTO);
        if (authUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(MESSAGE_SUCCESSFULLY);
    }
}
