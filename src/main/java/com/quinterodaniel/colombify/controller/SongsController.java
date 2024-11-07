package com.quinterodaniel.colombify.controller;

import com.google.gson.Gson;
import com.quinterodaniel.colombify.dto.ArtistDto;
import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/songs")
public class SongsController {

    private SongService songService;

    @Autowired
    public SongsController(SongService songService) {
        this.songService = songService;
    }
    @PostMapping
    public ResponseEntity handleSongFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(required = false, value = "body") String songToCreate) throws Exception {
        var song = new Gson().fromJson(songToCreate, SongDto.class);
        return ResponseEntity.ok(songService.createSong(song, file));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateSong(@PathVariable("id") Long id, @RequestBody SongDto songToUpdate) throws Exception {
        return ResponseEntity.ok(songService.updateSong(id, songToUpdate));
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

    @GetMapping("/find")
    public ResponseEntity getSongByIdentifier(@RequestParam("identifier") String identifier) {
        return ResponseEntity.ok(songService.getSongsByIdentifier(identifier));
    }

}
