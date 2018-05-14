package com.example.annualproject.userApi;

import com.example.annualproject.userApi.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAdapter {
    @Autowired
    static UserServices userServices;

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .pseudo(user.getPseudo())
                .token(user.getToken())
                .build();
    }

    public static User toUser(UserDTO dto) {
        return userServices.getUserByPseudo(dto.getPseudo());
    }
}