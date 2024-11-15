package com.quinterodaniel.colombify.service.impl;

import com.quinterodaniel.colombify.dto.PlaylistDto;
import com.quinterodaniel.colombify.entity.Playlist;
import com.quinterodaniel.colombify.repository.PlaylistRepository;
import com.quinterodaniel.colombify.repository.SongRepository;
import com.quinterodaniel.colombify.repository.UserRepository;
import com.quinterodaniel.colombify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void createPlaylist(Long userId, PlaylistDto playlistToCreate) {
        var user = userRepository.findById(userId).get();

        var playlist = Playlist.builder()
                .name(playlistToCreate.getName())
                .user(user)
                .build();

        playlistRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Optional<Playlist> getPlaylist(Long id, Long userId) {
        return playlistRepository.findPlaylistByIdAndUserId(id, userId);
    }

    @Override
    public List<Playlist> getPlaylists(Long userId) {
        return playlistRepository.findPlaylistsByUserId(userId);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, Long songId) {
        var playlist = playlistRepository.findById(playlistId).get();
        var song = songRepository.findById(songId).get();
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }
}
