package com.example.gamestore.service;

import com.example.gamestore.domain.dtos.UserLoginDto;
import com.example.gamestore.domain.dtos.UserRegisterDto;

public interface UserService {

   String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto loginDto);

    String  logout();
}
