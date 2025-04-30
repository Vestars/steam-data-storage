package com.vestars.steam_data_storage.repository;

import com.vestars.steam_data_storage.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
