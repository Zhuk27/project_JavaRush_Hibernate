package com.javaRush.module4.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "city", schema = "movie")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Short id;
    @Column(length = 50)
    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
