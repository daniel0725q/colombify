package com.quinterodaniel.colombify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponseDto {
    private Long id;
    private String name;
    private Long userId;
    private List<SongDto> songs;
}