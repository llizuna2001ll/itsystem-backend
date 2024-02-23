package com.llizuna2001ll.itsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String brand;
    private String reference;
    private String description;
    private String imagePath;
    private LocalDateTime creationDate;
    @ManyToOne
    private Category category;
}
