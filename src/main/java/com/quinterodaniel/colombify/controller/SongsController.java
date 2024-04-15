package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.ArtistDto;
import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongsController {

    private SongService songService;

    @Autowired
    public SongsController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity createSong(@RequestBody SongDto songToCreate) throws Exception {
        return ResponseEntity.ok(songService.createSong(songToCreate));
    }

    @PatchMapping
    public ResponseEntity updateSong(@RequestBody SongDto songToUpdate) throws Exception {
        return ResponseEntity.ok(songService.updateSong(songToUpdate));
    }

    @GetMapping
    public ResponseEntity getSongs() {
        return ResponseEntity.ok(songService.getSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity getSong(@PathVariable("id") Long id) {
        return ResponseEntity.ok(songService.getSong(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
