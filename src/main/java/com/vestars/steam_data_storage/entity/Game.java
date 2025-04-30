package com.vestars.steam_data_storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    private Long appId;
    private String name;
    private String developer;
    private String publisher;
    private String releaseDate;
    private Double price;
}
