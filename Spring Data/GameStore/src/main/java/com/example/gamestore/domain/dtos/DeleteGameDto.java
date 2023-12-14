package com.example.gamestore.domain.dtos;

import org.hibernate.validator.constraints.Length;

public class DeleteGameDto {

    private Long id;

    public DeleteGameDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
