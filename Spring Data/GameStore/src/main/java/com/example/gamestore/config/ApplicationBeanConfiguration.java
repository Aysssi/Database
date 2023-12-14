package com.example.gamestore.config;

import com.example.gamestore.utils.ValidationImpl;
import com.example.gamestore.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
      public ModelMapper modelMapper(){
          return new ModelMapper();
      }

      @Bean
      public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
      }


      @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidationImpl(validator());
      }

}
