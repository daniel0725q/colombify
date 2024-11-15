package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.PlaylistDto;
import com.quinterodaniel.colombify.entity.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    void createPlaylist(Long userId, PlaylistDto playlistToCreate);
    void deletePlaylist(Long id);
    Optional<Playlist> getPlaylist(Long id, Long userId);
    List<Playlist>  getPlaylists(Long userId);
    public void addSongToPlaylist(Long playlistId, Long songId);
}
