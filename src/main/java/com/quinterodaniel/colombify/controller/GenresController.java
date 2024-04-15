package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.GenreDto;
import com.quinterodaniel.colombify.service.GenreService;
import org.springframework.http.HttpStatus;
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

    @PatchMapping
    public ResponseEntity updateGenre(@RequestBody GenreDto genreToUpdate) throws Exception {
        return ResponseEntity.ok(genreService.updateGenre(genreToUpdate));
    }

    @GetMapping
    public ResponseEntity getGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity getGenre(@PathVariable("id") Long genreId) {
        return ResponseEntity.ok(genreService.getGenre(genreId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGenre(@PathVariable("id") Long genreId) {
        genreService.deleteGenre(genreId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
