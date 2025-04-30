package com.vestars.steam_data_storage.api;

import com.vestars.steam_data_storage.entity.Game;
import com.vestars.steam_data_storage.repository.GameRepository;
import com.vestars.steam_data_storage.service.SteamApiClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class SteamGameController {

    private final SteamApiClient steamApiClient;
    private final GameRepository gameRepository;

    public SteamGameController(SteamApiClient steamApiClient, GameRepository gameRepository) {
        this.steamApiClient = steamApiClient;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{appId}")
    public ResponseEntity<Game> fetchAndSaveGame(@PathVariable Long appId) {
        try {
            Game game = steamApiClient.fetchGameDetails(appId);
            gameRepository.save(game);
            return ResponseEntity.ok(game);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

