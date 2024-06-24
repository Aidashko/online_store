package com.example.online_store_1.models;

import com.example.online_store_1.enums.OperationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_operations")
@Getter
@Setter
@RequiredArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}
