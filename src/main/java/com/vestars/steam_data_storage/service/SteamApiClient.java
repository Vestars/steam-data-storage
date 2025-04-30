package com.vestars.steam_data_storage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vestars.steam_data_storage.entity.Game;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SteamApiClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Game fetchGameDetails(Long appId) throws JsonProcessingException {
        String url = "https://store.steampowered.com/api/appdetails?appids=" + appId;
        String response = restTemplate.getForObject(url, String.class);

        JsonNode root = objectMapper.readTree(response);
        JsonNode gameData = root.path(String.valueOf(appId)).path("data");

        Game game = new Game();
        game.setAppId(appId);
        game.setName(gameData.path("name").asText());
        game.setDeveloper(gameData.path("developers").get(0).asText());
        game.setPublisher(gameData.path("publishers").get(0).asText());
        game.setReleaseDate(gameData.path("release_date").path("date").asText());
        JsonNode priceNode = gameData.path("price_overview").path("final");
        if (!priceNode.isMissingNode()) {
            game.setPrice(priceNode.asDouble() / 100); // Convert cents to dollars
        }
        return game;
    }

}
