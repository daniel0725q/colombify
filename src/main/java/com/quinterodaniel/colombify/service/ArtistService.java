package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.ArtistDto;
import com.quinterodaniel.colombify.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist createArtist(ArtistDto artistToCreate);
    Optional<Artist> getArtist(Long id);
    List<Artist> getArtistsByName(String identifier);
    List<Artist> getAllArtists();
}
