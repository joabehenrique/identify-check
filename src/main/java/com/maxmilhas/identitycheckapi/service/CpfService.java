package com.maxmilhas.identitycheckapi.service;

import com.maxmilhas.identitycheckapi.dto.CpfDTO;
import com.maxmilhas.identitycheckapi.entity.Cpf;
import com.maxmilhas.identitycheckapi.exception.CpfNotFoundException;
import com.maxmilhas.identitycheckapi.exception.DatabaseException;
import com.maxmilhas.identitycheckapi.exception.ExistsCpfException;
import com.maxmilhas.identitycheckapi.exception.InvalidCpfException;
import com.maxmilhas.identitycheckapi.helper.CpfHelper;
import com.maxmilhas.identitycheckapi.repository.CpfRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class CpfService {
    @Autowired
    private CpfRepository cpfRepository;
    @Autowired
    private CpfHelper cpfHelper;

    public Page<CpfDTO> findAllPaged(Pageable page){
        Page<Cpf> cpfs = cpfRepository.findAll(page);
        return cpfs.map(CpfDTO::new);
    }

    public CpfDTO findByCpf(String cpf) {
        if (!CpfHelper.isValid(cpf)) {
            throw new InvalidCpfException("Invalid CPF");
        }
        Optional<Cpf> result = cpfRepository.findByCpf(cpf);
        if (result.isEmpty()) {
            throw new CpfNotFoundException("CPF not found: " + cpf);
        }
        CpfDTO dto = new CpfDTO(result.get());
        return dto;
    }

    public CpfDTO saveCpf(CpfDTO cpf){
        String cpfValue = cpf.getCpf().replaceAll("\\D+", "");
        if (!CpfHelper.isValid(cpfValue)) {
            throw new InvalidCpfException("Invalid CPF");
        }
        Optional<Cpf> existingCpf = cpfRepository.findByCpf(cpfValue);
        if (existingCpf.isPresent()) {
            throw new ExistsCpfException("CPF "+cpfValue+" already exists");
        }
        Cpf newCpf = new Cpf();
        newCpf.setCpf(cpfValue);
        newCpf.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        newCpf = cpfRepository.save(newCpf);
        return new CpfDTO(newCpf);
    }

    public CpfDTO updateCpf(String cpf, CpfDTO newCpf){
        try {
            Optional<Cpf> accountEntity = cpfRepository.findByCpf(cpf);
            Cpf entity = accountEntity.orElseThrow(() -> new CpfNotFoundException("Cpf not found: "+ cpf));
            cpfHelper.convertDTOtoEntity(entity, newCpf);
            entity = cpfRepository.save(entity);
            return new CpfDTO(entity);
        }catch (EntityNotFoundException e){
            throw new CpfNotFoundException("ID cpf not found: "+cpf);
        }
    }

    public void deleteCpf(String cpf){
        if (!CpfHelper.isValid(cpf)) {
            throw new InvalidCpfException("Invalid CPF");
        }
        if (!cpfRepository.existsByCpf(cpf)) {
            throw new CpfNotFoundException("CPF not found: " + cpf);
        }
        try {
            cpfRepository.deleteByCpf(cpf);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
