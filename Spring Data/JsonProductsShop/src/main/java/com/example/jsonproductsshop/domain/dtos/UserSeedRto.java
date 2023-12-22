package com.example.jsonproductsshop.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;


public class UserSeedRto {
     @Expose
    private String firstName;
     @Expose
    private String lastName;
     @Expose
    private int age;

    public UserSeedRto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Length(min = 3, message = "Last name should be at least 3 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
