package com.quinterodaniel.colombify.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDto {
    private Long id;
    private String stageName;
    private String bio;
    private List<SongDto> songs;
}
