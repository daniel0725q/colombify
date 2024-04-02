package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.ArtistDto;
import com.quinterodaniel.colombify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
public class ArtistsController {
    ArtistService artistService;

    @Autowired
    public ArtistsController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity createArtist(@RequestBody ArtistDto artist) {
        return ResponseEntity.ok(artistService.createArtist(artist));
    }

    @GetMapping
    public ResponseEntity getArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }
}
