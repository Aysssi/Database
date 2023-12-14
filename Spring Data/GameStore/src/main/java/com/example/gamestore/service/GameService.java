package com.example.gamestore.service;

import com.example.gamestore.domain.dtos.AddGameDto;
import com.example.gamestore.domain.dtos.DeleteGameDto;
import com.example.gamestore.domain.dtos.UserDto;

public interface GameService {
    String addGame(AddGameDto addGameDto);

    void setLoggedUser(UserDto userDto);

    String deleteGame(DeleteGameDto deleteGameDto);
}
