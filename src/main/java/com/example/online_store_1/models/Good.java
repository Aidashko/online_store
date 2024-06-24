package com.example.online_store_1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Entity
@Table(name = "tb_good")
@Getter
@Setter
@RequiredArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double rating;
    private Long quantity;
    @ManyToOne
    private Category category;
    @OneToMany(targetEntity = Image.class)
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private List<Image> images;

}
