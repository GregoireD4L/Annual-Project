package com.example.annualproject.userApi.Service;

import org.springframework.stereotype.Service;


@Service
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String pseudo, String password);
}