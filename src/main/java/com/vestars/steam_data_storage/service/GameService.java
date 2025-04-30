package com.vestars.steam_data_storage.service;

import com.vestars.steam_data_storage.entity.Game;
import com.vestars.steam_data_storage.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void createGame(Game game) {
       this.gameRepository.save(game);
    }

}
