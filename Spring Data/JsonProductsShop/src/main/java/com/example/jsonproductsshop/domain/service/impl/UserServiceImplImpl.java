package com.example.jsonproductsshop.domain.service.impl;

import com.example.jsonproductsshop.domain.dtos.UserSeedRto;
import com.example.jsonproductsshop.domain.entities.User;
import com.example.jsonproductsshop.domain.repositories.UserRepository;
import com.example.jsonproductsshop.domain.service.UserService;
import com.example.jsonproductsshop.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
public class UserServiceImplImpl implements UserService {
     private final ModelMapper modelMapper;
     private final ValidatorUtil validatorUtil;

     private final UserRepository userRepository;

     @Autowired
    public UserServiceImplImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(UserSeedRto[] userSeedRtos) {

       if (this.userRepository.count() != 0){
           return;
       }

        Arrays.stream(userSeedRtos)
                .forEach(userSeedRto -> {
                    if (this.validatorUtil.isValid(userSeedRto)){
                        User user = this.modelMapper
                                .map(userSeedRto,User.class);

                     this.userRepository.saveAndFlush(user);

                    }else {

                        this.validatorUtil.violations(userSeedRto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });

    }

    @Override
    public User getRandomUser() {
         Random random = new Random();
         long randomId = random.nextInt((int) this.userRepository.count()) +1;

        return this.userRepository.getOne(randomId);
    }
}
