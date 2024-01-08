package com.joelmaciel.authservice.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewUserDTO {

    private String userName;
    private String password;
    private String role;
}
