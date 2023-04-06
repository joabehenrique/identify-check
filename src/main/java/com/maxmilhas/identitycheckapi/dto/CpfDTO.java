package com.maxmilhas.identitycheckapi.dto;

import com.maxmilhas.identitycheckapi.entity.Cpf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CpfDTO {
    private Long id;
    private String cpf;
    private LocalDateTime createdAt;

    public CpfDTO(Cpf cpfEntity) {
        this.id = cpfEntity.getId();
        this.cpf = cpfEntity.getCpf();
        this.createdAt = cpfEntity.getCreatedAt();
    }
}
