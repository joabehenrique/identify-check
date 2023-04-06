package com.maxmilhas.identitycheckapi.repository;

import com.maxmilhas.identitycheckapi.entity.Cpf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class CpfRepositoryTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("identitycheckapi")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private CpfRepository cpfRepository;

    private Cpf cpf;

    @BeforeEach
    public void setUp() {
        cpf = new Cpf(null, "123.456.789-01", LocalDateTime.now());
        cpf = cpfRepository.save(cpf);
    }

    @AfterEach
    public void tearDown() {
        cpfRepository.deleteAll();
    }

    @Test
    public void testSaveCpf() {
        Cpf newCpf = new Cpf(null, "234.567.890-12", LocalDateTime.now());
        Cpf savedCpf = cpfRepository.save(newCpf);

        assertEquals(newCpf.getCpf(), savedCpf.getCpf());
    }

    @Test
    public void testFindById() {
        Optional<Cpf> foundCpf = cpfRepository.findById(cpf.getId());
        assertTrue(foundCpf.isPresent());
        assertEquals(cpf.getCpf(), foundCpf.get().getCpf());
    }

    @Test
    public void testDeleteCpf() {
        cpfRepository.deleteById(cpf.getId());
        Optional<Cpf> foundCpf = cpfRepository.findById(cpf.getId());
        assertTrue(foundCpf.isEmpty());
    }
}

