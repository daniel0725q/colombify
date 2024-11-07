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

    @GetMapping("/{id}")
    public ResponseEntity getArtist(@PathVariable("id") Long id) {
        var artistOptional = artistService.getArtist(id);
        if (artistOptional.isPresent())
            return ResponseEntity.ok(artistService.getArtist(id));
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/find")
    public ResponseEntity getArtistByString(@RequestParam("identifier") String identifier) {
        var artists = artistService.getArtistsByName(identifier);
        return ResponseEntity.ok(artists);
    }
}
