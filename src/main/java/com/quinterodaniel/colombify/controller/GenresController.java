package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.GenreDto;
import com.quinterodaniel.colombify.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private GenreService genreService;

    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity createGenre(@RequestBody GenreDto genreToCreate) {
        return ResponseEntity.ok(genreService.createGenre(genreToCreate));
    }

    @GetMapping
    public ResponseEntity getGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
