package com.example.jsonproductsshop.configuration;

import com.example.jsonproductsshop.util.FileUtilImpl;
import com.example.jsonproductsshop.util.ValidatorUtil;
import com.example.jsonproductsshop.util.ValidatorUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public Gson gson() {
        return  new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public FileUtilImpl fileUtilImpl(){
        return  new FileUtilImpl();
    }

    @Bean
    public Validator validator(){
        return  Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtil validatorUtilImpl(){
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
