package com.example.gamestore.service;

import com.example.gamestore.domain.dtos.UserDto;
import com.example.gamestore.domain.dtos.UserLoginDto;
import com.example.gamestore.domain.dtos.UserRegisterDto;
import com.example.gamestore.domain.entities.Role;
import com.example.gamestore.domain.entities.User;
import com.example.gamestore.domain.repositories.UserRepository;
import com.example.gamestore.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements  UserService{
     private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private  UserDto loggedUser;
    private final GameService gameService;


    public UserServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepository, GameService gameService) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
         StringBuilder sb = new StringBuilder();
         if(userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
             sb.append("Password don't match");

         } else if (this.validatorUtil.isValid(userRegisterDto)){
             User user = this.modelMapper.map(userRegisterDto,User.class);

             if (this.userRepository.count() == 0){
                 user.setRole(Role.ADMIN);
             }else {
                 user.setRole(Role.USER);
             }

             sb.append(String.format("%s was registered", userRegisterDto.getFullName()));
             this.userRepository.saveAndFlush(user);

         }else {

             Set<ConstraintViolation<UserRegisterDto>> violations = this.validatorUtil.violations(userRegisterDto);
            violations.forEach(e-> sb.append(String.format("%s %n", e.getMessage())));
         }



        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto loginDto) {
        StringBuilder sb = new StringBuilder();
        Optional<User> user = this.userRepository.findAllByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (user.isPresent()){

          if (this.loggedUser != null){
              sb.append("User is already logged in.");
          }else {
              this.loggedUser = this.modelMapper.map(user.get(),UserDto.class);
              sb.append(String.format("Successfully logged in %s", user.get().getFullName()));
              this.gameService.setLoggedUser(this.loggedUser);
          }
        }else {
         sb.append("Incorrect email / password");
        }
        return sb.toString();
    }

    @Override
    public String logout() {
        if (this.loggedUser == null){
         return "Cannot log out. No user was logged in";

        }else{

          String message = String.format("User %s successfully logged out", this.loggedUser.getFullName());
          this.loggedUser = null;
          return message;
        }

    }
}
