package com.colonia.backend.Controller;

import com.colonia.backend.Database.Entitty.ChamadoEntity;
import com.colonia.backend.Dto.ChamadoDto;
import com.colonia.backend.Service.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService chamadoService;

    @GetMapping("/chamados")
    public ResponseEntity<List<ChamadoEntity>> findAll() {
        return chamadoService.findAll();
    }

    @GetMapping("/chamados/{id}")
    public ResponseEntity<ChamadoEntity> findById(@PathVariable int id) {
        return  chamadoService.findById(id);
    }

    @PostMapping("/chamados")
    public ResponseEntity<ChamadoEntity> createCalled(@RequestBody ChamadoDto dto) {
        return chamadoService.createCalled(dto);
    }

    @PutMapping("/chamados/{id}")
    public ResponseEntity<ChamadoEntity> editCalled(@PathVariable int id, @RequestBody ChamadoDto dto) {
        return chamadoService.editCalled(id, dto);
    }

    @DeleteMapping("/chamados/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        return chamadoService.deleteById(id);
    }

    @GetMapping("/resolver-chamado/{id}")
    public ResponseEntity<ChamadoEntity> resolverChamado(@PathVariable int  id) {
        return chamadoService.resolverChamado(id);
    }



}
