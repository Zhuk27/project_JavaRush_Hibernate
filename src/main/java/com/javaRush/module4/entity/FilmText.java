package com.javaRush.module4.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "film_text", schema = "movie")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;
    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

}
