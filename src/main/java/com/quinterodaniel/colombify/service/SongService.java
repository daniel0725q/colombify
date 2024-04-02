package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.entity.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Song createSong(SongDto song) throws Exception;
    Optional<Song> getSong(Long id);
    List<Song> getSongs();
}