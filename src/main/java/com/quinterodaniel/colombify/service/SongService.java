package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.entity.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Song createSong(SongDto song, MultipartFile file) throws Exception;
    Optional<Song> getSong(Long id);
    List<Song> getSongs();

    Song updateSong(Long id, SongDto song) throws Exception;

    void deleteSong(Long id);

    List<Song> getSongsByIdentifier(String identifier);
}
