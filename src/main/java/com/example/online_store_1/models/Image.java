package com.example.online_store_1.models;

import com.example.online_store_1.enums.ImageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_images")
@Getter
@Setter
@RequiredArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    @ManyToOne
    @JsonIgnore
    private Good good;
    @ManyToOne
    @JsonIgnore
    private User user;
    private ImageType type;
    private boolean isActive;

}
