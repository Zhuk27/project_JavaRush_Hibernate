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
@Table(name = "staff", schema = "movie")
public class Staff {
    @Id
    @GeneratedValue
    @Column(name = "staff_id")
    private Byte id;
    @Column(name = "first_name",length = 45, nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 45, nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;
    @Column(length = 50)
    private String email;
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(nullable = false)
    private Byte active;
    @Column(length = 16, nullable = false)
    private String username;
    @Column(length = 40, nullable = false)
    private String password;
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
