package com.example.annualproject.userApi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {

    private String pseudo;
    private String email;
    private String token;
}