package com.example.online_store_1.models;

import com.example.online_store_1.models.dto.RegistrationRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fio;
    private String email;
    private boolean isActive;
    private Integer activationCode;
    @OneToMany(targetEntity = Image.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Image> images;
    @ManyToMany
    @JoinTable(name = "tb_user_roles",
            joinColumns = {@JoinColumn(name = "user_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    })
    private Set<Role> roles;

    public User(RegistrationRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.fio = requestDto.getFio();
        this.email = requestDto.getEmail();
        this.isActive = false;
    }

    public void setIsActive(boolean b) {
    }
}
