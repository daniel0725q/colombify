package com.quinterodaniel.colombify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String stageName;

    @Column(nullable = false)
    private String bio;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("artist")
    private List<Song> songs;
}
