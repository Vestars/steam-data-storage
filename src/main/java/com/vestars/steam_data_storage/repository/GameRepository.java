package com.vestars.steam_data_storage.repository;

import com.vestars.steam_data_storage.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
