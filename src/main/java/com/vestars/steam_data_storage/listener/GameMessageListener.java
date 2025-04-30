package com.vestars.steam_data_storage.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestars.steam_data_storage.entity.Game;
import com.vestars.steam_data_storage.repository.GameRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GameMessageListener {

    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GameMessageListener(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @RabbitListener(queues = "steam-game-queue")
    public void receiveMessage(String message) throws JsonProcessingException {
        Game game = objectMapper.readValue(message, Game.class);
        gameRepository.save(game);
    }
}
