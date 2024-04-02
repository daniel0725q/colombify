package com.quinterodaniel.colombify.service.impl;

import com.quinterodaniel.colombify.dto.GenreDto;
import com.quinterodaniel.colombify.entity.Genre;
import com.quinterodaniel.colombify.repository.GenreRepository;
import com.quinterodaniel.colombify.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(GenreDto genreToCreate) {
        var genre = Genre.builder()
                .name(genreToCreate.getName())
                .description(genreToCreate.getDescription())
                .colorInHex(genreToCreate.getColorInHex())
                .build();
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public Optional<Genre> getGenre(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public Genre updateGenre(GenreDto genreDto) {
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteGenre(Long genreId) {

    }
}