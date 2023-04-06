package com.maxmilhas.identitycheckapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cpfs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cpf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private LocalDateTime createdAt;
}
