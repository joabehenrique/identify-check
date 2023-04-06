package com.maxmilhas.identitycheckapi.repository;

import com.maxmilhas.identitycheckapi.entity.Cpf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CpfRepository extends JpaRepository<Cpf, Long> {
    Optional<Cpf> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    void deleteByCpf(String cpf);
}
