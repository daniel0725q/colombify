package com.quinterodaniel.colombify.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
    private Long id;
    private String title;
    private String audioUrl;
    private String artwork;
    private String description;
    private Long artistId;
    private Long genreId;
}
