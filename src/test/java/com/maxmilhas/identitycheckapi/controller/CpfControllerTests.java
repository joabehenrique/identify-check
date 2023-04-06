package com.maxmilhas.identitycheckapi.controller;

import com.maxmilhas.identitycheckapi.dto.CpfDTO;
import com.maxmilhas.identitycheckapi.service.CpfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class CpfControllerTests {
    @InjectMocks
    private CpfController cpfController;

    @Mock
    private CpfService cpfService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cpfController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        CpfDTO cpf1 = new CpfDTO(1L, "123.456.789-01", LocalDateTime.now());
        CpfDTO cpf2 = new CpfDTO(2L, "234.567.890-12", LocalDateTime.now());
        Page<CpfDTO> page = new PageImpl<>(Arrays.asList(cpf1, cpf2));

        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt"));

        when(cpfService.findAllPaged(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/v1/cpf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].cpf").value("123.456.789-01"))
                .andExpect(jsonPath("$.content[1].cpf").value("234.567.890-12"));

        verify(cpfService, times(1)).findAllPaged(pageable);
    }

    @Test
    public void testFindByCpf() throws Exception {
        CpfDTO cpf = new CpfDTO(1L, "123.456.789-01", LocalDateTime.now());

        when(cpfService.findByCpf("123.456.789-01")).thenReturn(cpf);

        mockMvc.perform(get("/api/v1/cpf/{cpf}", "123.456.789-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("123.456.789-01"));

        verify(cpfService, times(1)).findByCpf("123.456.789-01");
    }

    @Test
    public void testSaveCpf() throws Exception {
        CpfDTO cpf = new CpfDTO(1L, "123.456.789-01", LocalDateTime.now());

        when(cpfService.saveCpf(any(CpfDTO.class))).thenReturn(cpf);

        mockMvc.perform(post("/api/v1/cpf")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"123.456.789-01\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("123.456.789-01"));

        verify(cpfService, times(1)).saveCpf(any(CpfDTO.class));
    }

    @Test
    public void testUpdateCpf() throws Exception {
        CpfDTO cpf = new CpfDTO(1L, "234.567.890-12", LocalDateTime.now());

        when(cpfService.updateCpf(eq("123.456.789-01"), any(CpfDTO.class))).thenReturn(cpf);

        mockMvc.perform(put("/api/v1/cpf/{cpf}", "123.456.789-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"234.567.890-12\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("234.567.890-12"));

        verify(cpfService, times(1)).updateCpf(eq("123.456.789-01"), any(CpfDTO.class));
    }

    @Test
    public void testDeleteCpf() throws Exception {
        doNothing().when(cpfService).deleteCpf("123.456.789-01");

        mockMvc.perform(delete("/api/v1/cpf/{cpf}", "123.456.789-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cpfService, times(1)).deleteCpf("123.456.789-01");
    }
}
