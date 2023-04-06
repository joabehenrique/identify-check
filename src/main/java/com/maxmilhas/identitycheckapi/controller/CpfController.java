package com.maxmilhas.identitycheckapi.controller;

import com.maxmilhas.identitycheckapi.dto.CpfDTO;
import com.maxmilhas.identitycheckapi.exception.CpfNotFoundException;
import com.maxmilhas.identitycheckapi.exception.DatabaseException;
import com.maxmilhas.identitycheckapi.exception.InvalidCpfException;
import com.maxmilhas.identitycheckapi.service.CpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/cpf")
public class CpfController {

    @Autowired
    public CpfService cpfService;

    @GetMapping
    public ResponseEntity<Page<CpfDTO>> findAll(Pageable pageRequest){
        Page<CpfDTO> list = cpfService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Object> findByCpf(@PathVariable String cpf){
        try {
            CpfDTO cpfFind = cpfService.findByCpf(cpf);
            return ResponseEntity.ok().body(cpfFind);
        } catch (InvalidCpfException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CpfNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<CpfDTO> saveMovie(@RequestBody CpfDTO cpfBody){
        CpfDTO cpf = cpfService.saveCpf(cpfBody);
        return new ResponseEntity<>(cpf, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cpf}")
    public ResponseEntity<CpfDTO> updateMovie(@RequestBody  CpfDTO cpfBody, @PathVariable String cpf){
        CpfDTO cpfNew = cpfService.updateCpf(cpf, cpfBody);
        return new ResponseEntity<>(cpfNew, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<?> deleteMovie(@PathVariable String cpf){
        try {
            cpfService.deleteCpf(cpf);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidCpfException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CpfNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DatabaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
