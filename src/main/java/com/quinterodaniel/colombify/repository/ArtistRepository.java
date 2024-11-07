package com.quinterodaniel.colombify.repository;

import com.quinterodaniel.colombify.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findAll();
    List<Artist> findArtistByStageNameStartsWith(String stageName);
}
