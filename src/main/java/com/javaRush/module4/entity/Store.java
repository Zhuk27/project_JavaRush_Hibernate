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
@Table(name = "store", schema = "movie")
public class Store {
    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Byte id;
    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
