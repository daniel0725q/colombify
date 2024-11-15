package com.quinterodaniel.colombify.repository;

import com.quinterodaniel.colombify.entity.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    List<Playlist> findPlaylistsByUserId(Long userId);

    Optional<Playlist> findPlaylistByIdAndUserId(Long id, Long userId);
}
