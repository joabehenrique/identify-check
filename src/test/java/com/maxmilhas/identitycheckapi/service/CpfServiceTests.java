package com.maxmilhas.identitycheckapi.service;

import com.maxmilhas.identitycheckapi.dto.CpfDTO;
import com.maxmilhas.identitycheckapi.entity.Cpf;
import com.maxmilhas.identitycheckapi.exception.CpfNotFoundException;
import com.maxmilhas.identitycheckapi.exception.ExistsCpfException;
import com.maxmilhas.identitycheckapi.exception.InvalidCpfException;
import com.maxmilhas.identitycheckapi.helper.CpfHelper;
import com.maxmilhas.identitycheckapi.repository.CpfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CpfServiceTests {

    @Autowired
    private CpfService cpfService;

    @MockBean
    private CpfRepository cpfRepository;

    @MockBean
    private CpfHelper cpfHelper;

    private Cpf cpf1;
    private Cpf cpf2;
    private Page<Cpf> cpfsPage;

    @BeforeEach
    public void setUp() {
        cpf1 = new Cpf(1L, "123.456.789-01", LocalDateTime.now());
        cpf2 = new Cpf(2L, "234.567.890-12", LocalDateTime.now());

        cpfsPage = new PageImpl<>(Arrays.asList(cpf1, cpf2));

        when(cpfRepository.findAll(any(PageRequest.class))).thenReturn(cpfsPage);
        when(cpfRepository.findById(1L)).thenReturn(Optional.of(cpf1));
        when(cpfRepository.findById(2L)).thenReturn(Optional.of(cpf2));
        when(cpfRepository.save(any(Cpf.class))).thenReturn(cpf1);
    }

    @Test
    public void findAllPagedTest() {
        Page<CpfDTO> result = cpfService.findAllPaged(PageRequest.of(0, 10));
        assertEquals(2, result.getTotalElements());
    }

    @Test
    public void findByIdTest() {
        CpfDTO result = cpfService.findByCpf("123.456.789-01");
        assertEquals(cpf1.getCpf(), result.getCpf());
    }

    @Test
    public void testSaveValidCpf() {
        String cpf = "63793028089";

        if (!CpfHelper.isValid(cpf)) {
            fail("Invalid CPF: " + cpf);
        }

        CpfDTO newCpf = new CpfDTO(1L, cpf, LocalDateTime.now());
        CpfDTO savedCpf = cpfService.saveCpf(newCpf);

        assertNotNull(savedCpf);
        assertTrue(CpfHelper.isValid(savedCpf.getCpf()));
        assertNotNull(savedCpf.getCreatedAt());
    }

    @Test
    public void testSaveInvalidCpfThrowsInvalidCpfException() {
        CpfDTO invalidCpf = new CpfDTO(1L, "12345678900", LocalDateTime.now());

        assertThrows(InvalidCpfException.class, () -> cpfService.saveCpf(invalidCpf));
    }

    @Test
    public void testSaveExistingCpfThrowsExistsCpfException() {
        String cpf = "123.456.789-01";

        if (!CpfHelper.isValid(cpf)) {
            fail("Invalid CPF: " + cpf);
        }

        CpfDTO existingCpf = new CpfDTO(1L, cpf, LocalDateTime.now());
        //cpfService.saveCpf(existingCpf);

        assertThrows(ExistsCpfException.class, () -> cpfService.saveCpf(existingCpf));
    }

    @Test
    public void updateCpfTest() {
        CpfDTO newCpfDTO = new CpfDTO(null, "345.678.901-22", LocalDateTime.now());
        CpfDTO result = cpfService.updateCpf("123.456.789-01", newCpfDTO);

        verify(cpfHelper, times(1)).convertDTOtoEntity(cpf1, newCpfDTO);
        assertEquals(cpf1.getCpf(), result.getCpf());
    }

    @Test
    public void deleteCpfTest() {
        CpfDTO cpf = new CpfDTO(1L, "123.456.789-01", LocalDateTime.now());
        when(cpfRepository.existsById(cpf.getId())).thenReturn(true);

        //cpfService.deleteCpf(cpf.getCpf());

        verify(cpfRepository, times(1)).deleteById(cpf.getId());
    }

    @Test
    public void deleteCpfNotFoundTest() {
        long id = 3L;
        assertThrows(CpfNotFoundException.class, () -> cpfService.deleteCpf(String.valueOf(id)));
    }
}

