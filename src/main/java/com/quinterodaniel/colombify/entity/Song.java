package com.quinterodaniel.colombify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("songs")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("songs")
    private Artist artist;
}
