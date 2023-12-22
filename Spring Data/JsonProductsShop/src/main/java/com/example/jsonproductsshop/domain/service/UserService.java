package com.example.jsonproductsshop.domain.service;

import com.example.jsonproductsshop.domain.dtos.UserSeedRto;
import com.example.jsonproductsshop.domain.entities.User;

public interface UserService {
    void seedUsers(UserSeedRto[] userSeedRtos);

    User getRandomUser();
}
