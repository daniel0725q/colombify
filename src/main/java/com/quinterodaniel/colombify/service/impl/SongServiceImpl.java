package com.quinterodaniel.colombify.service.impl;

import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.entity.Song;
import com.quinterodaniel.colombify.repository.SongRepository;
import com.quinterodaniel.colombify.service.ArtistService;
import com.quinterodaniel.colombify.service.GenreService;
import com.quinterodaniel.colombify.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;

    private ArtistService artistService;

    private GenreService genreService;

    public SongServiceImpl(SongRepository songRepository, ArtistService artistService,
                           GenreService genreService) {
        this.songRepository = songRepository;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @Override
    public Song createSong(SongDto songToCreate) throws Exception {
        var artistOptional = artistService.getArtist(songToCreate.getArtistId());
        if (artistOptional.isEmpty()) {
            throw new Exception("");
        }

        var genreOptional = genreService.getGenre(songToCreate.getGenreId());
        if (genreOptional.isEmpty()) {
            throw new Exception("");
        }

        var artist = artistOptional.get();
        var genre = genreOptional.get();
        var song = Song.builder()
                .artist(artist)
                .artwork(songToCreate.getArtwork())
                .title(songToCreate.getTitle())
                .genre(genre)
                .audioUrl(songToCreate.getAudioUrl())
                .description(songToCreate.getDescription())
                .build();

        songRepository.save(song);
        return song;
    }

    @Override
    public Optional<Song> getSong(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }
}
