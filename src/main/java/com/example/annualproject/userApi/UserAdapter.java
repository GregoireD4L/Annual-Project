package com.example.annualproject.userApi;

public class UserAdapter {

    public static UserDTO toDto(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .pseudo(user.getPseudo())
                .token(user.getToken())
                .build();
    }

    public static User toUser(UserDTO dto){
        return User.builder()
                .pseudo(dto.getPseudo())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .token(dto.getToken())
                .build();
    }
}