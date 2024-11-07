package com.quinterodaniel.colombify.service.impl;

import com.quinterodaniel.colombify.dto.ArtistDto;
import com.quinterodaniel.colombify.entity.Artist;
import com.quinterodaniel.colombify.repository.ArtistRepository;
import com.quinterodaniel.colombify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist createArtist(ArtistDto artistToCreate) {
        var artist = Artist.builder()
                .songs(new ArrayList<>())
                .stageName(artistToCreate.getStageName())
                .bio(artistToCreate.getBio())
                .build();

        artistRepository.save(artist);
        return artist;
    }

    @Override
    public Optional<Artist> getArtist(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public List<Artist> getArtistsByName(String identifier) {
        return artistRepository.findArtistByStageNameStartsWith(identifier);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
}
