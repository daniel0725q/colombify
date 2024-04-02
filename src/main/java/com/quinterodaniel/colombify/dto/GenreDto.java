package com.quinterodaniel.colombify.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDto {
    private Long id;
    private String name;
    private String description;
    private String colorInHex;
    private List<SongDto> songs;
}
