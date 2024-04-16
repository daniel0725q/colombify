package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.GenreDto;
import com.quinterodaniel.colombify.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre createGenre(GenreDto genreToCreate);
    Optional<Genre> getGenre(Long genreId);
    Genre updateGenre(Long id, GenreDto genreDto) throws Exception;
    List<Genre> getAllGenres();
    void deleteGenre(Long genreId);
}
