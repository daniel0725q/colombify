package com.quinterodaniel.colombify.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String audioUrl;

    @Column(nullable = false)
    private String artwork;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Artist artist;
}
