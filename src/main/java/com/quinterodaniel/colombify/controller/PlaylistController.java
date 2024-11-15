package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.PlaylistDto;
import com.quinterodaniel.colombify.dto.PlaylistResponseDto;
import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.entity.Playlist;
import com.quinterodaniel.colombify.service.PlaylistService;
import com.quinterodaniel.colombify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final UserService userService;

    private Long getIdFromAuthentication(Authentication authentication) {
        var user = userService.findUserByEmail(authentication.getName());
        return user.getId();
    }

    @Autowired
    public PlaylistController(PlaylistService playlistService, UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistResponseDto>> getPlaylists(@Autowired Authentication authentication) {
        var playlists = playlistService.getPlaylists(getIdFromAuthentication(authentication))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playlists);
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@Autowired Authentication authentication, @RequestBody PlaylistDto playlist) {
        playlistService.createPlaylist(getIdFromAuthentication(authentication), playlist);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponseDto> getPlaylist(@Autowired Authentication authentication, @PathVariable("id") Long id) {
        var playlist = playlistService.getPlaylist(id, getIdFromAuthentication(authentication)).orElseThrow();
        return ResponseEntity.ok(convertToDto(playlist));
    }

    @PutMapping("/{id}/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable("id") Long playlistId, @PathVariable("songId") Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok().build();
    }

    private PlaylistResponseDto convertToDto(Playlist playlist) {
        return PlaylistResponseDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .userId(playlist.getUser().getId())
                .songs(playlist.getSongs().stream()
                        .map(song -> SongDto.builder()
                                .id(song.getId())
                                .title(song.getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}