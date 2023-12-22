package com.example.cardealerjson.domain.dtos.implDto;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class CustomerSeedDto {
    @Expose
    private String name;
    @Expose
    public String birthDate;
    @Expose
    private boolean isYoungDriver;

    public CustomerSeedDto(String name, String birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String  birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
