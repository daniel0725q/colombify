package com.quinterodaniel.colombify.service.impl;

import com.quinterodaniel.colombify.dto.SongDto;
import com.quinterodaniel.colombify.entity.Song;
import com.quinterodaniel.colombify.repository.ArtistRepository;
import com.quinterodaniel.colombify.repository.GenreRepository;
import com.quinterodaniel.colombify.repository.SongRepository;
import com.quinterodaniel.colombify.service.ArtistService;
import com.quinterodaniel.colombify.service.GenreService;
import com.quinterodaniel.colombify.service.SongService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;

    private ArtistService artistService;

    private GenreService genreService;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    private final EntityManager entityManager;

    public SongServiceImpl(SongRepository songRepository, ArtistService artistService,
                           GenreService genreService,
                           ArtistRepository artistRepository,
                           GenreRepository genreRepository,
                           EntityManager entityManager) {
        this.songRepository = songRepository;
        this.artistService = artistService;
        this.genreService = genreService;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.entityManager = entityManager;
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
        var artistSongs = artist.getSongs();
        artistSongs.add(song);
        artist.setSongs(artistSongs);
        artistRepository.save(artist);
        var genreSongs = genre.getSongs();
        genreSongs.add(song);
        genre.setSongs(genreSongs);
        genreRepository.save(genre);
        return song;
    }

    @Override
    public Optional<Song> getSong(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song updateSong(SongDto songDto) throws Exception {
        var songOptional = songRepository.findById(songDto.getId());
        if (songOptional.isEmpty()) {
            throw new Exception("Song not found");
        }
        var song = songOptional.get();

        var artistOptional = artistService.getArtist(songDto.getArtistId());
        if (artistOptional.isEmpty()) {
            throw new Exception("Artist not found");
        }
        var artist = artistOptional.get();

        song.setDescription(songDto.getDescription());
        song.setTitle(songDto.getTitle());
        song.setArtist(artist);
        song.setArtwork(songDto.getArtwork());
        song.setAudioUrl(songDto.getAudioUrl());

        return songRepository.save(song);
    }

    @Override
    @Transactional
    public void deleteSong(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + id));

        entityManager.createNativeQuery("DELETE FROM artist_songs WHERE songs_id = :songId")
                .setParameter("songId", id)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM genre_songs WHERE songs_id = :songId")
                .setParameter("songId", id)
                .executeUpdate();

        songRepository.delete(song);
    }
}
