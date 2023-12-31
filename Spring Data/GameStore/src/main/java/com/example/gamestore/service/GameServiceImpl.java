package com.example.gamestore.service;

import com.example.gamestore.domain.dtos.AddGameDto;
import com.example.gamestore.domain.dtos.DeleteGameDto;
import com.example.gamestore.domain.dtos.UserDto;
import com.example.gamestore.domain.entities.Game;
import com.example.gamestore.domain.entities.Role;
import com.example.gamestore.domain.repositories.GameRepository;
import com.example.gamestore.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final ValidatorUtil validatorUtil;
    private UserDto userDto;


    @Autowired
    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;

        this.validatorUtil = validatorUtil;
    }

    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();
        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append(("Invalid logged in user."));

        } else if (this.validatorUtil.isValid(addGameDto)) {
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);

            sb.append(String.format("Added %s", game.getTitle()));
        } else {
            this.validatorUtil.violations(addGameDto)
                    .forEach(e -> sb.append(e.getMessage()).append(System.lineSeparator()));
        }

        return sb.toString();
    }

    @Override
    public void setLoggedUser(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String deleteGame(DeleteGameDto deleteGameDto) {
        StringBuilder sb = new StringBuilder();
        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append(("Invalid logged in user."));

        }else  {
            Optional<Game> game = this.gameRepository.findById(deleteGameDto.getId());

            if(game.isPresent()){
              this.gameRepository.delete(game.get());
              sb.append(String.format("Came %s was delete.", game.get().getTitle()));

            }else {
                sb.append("Cannot find game");
            }
        }
        return sb.toString();

    }
}