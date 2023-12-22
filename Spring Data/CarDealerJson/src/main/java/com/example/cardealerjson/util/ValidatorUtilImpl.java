package com.example.cardealerjson.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil{
    private final Validator validator;
    @Autowired
    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return false;
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E entity) {
        return null;
    }
}
