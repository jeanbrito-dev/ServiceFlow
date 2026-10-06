package com.colonia.backend.Service;

import com.colonia.backend.Database.Entitty.ChamadoEntity;
import com.colonia.backend.Database.Repository.ChamadoRepository;
import com.colonia.backend.Dto.ChamadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ResponseEntity<List<ChamadoEntity>> findAll() {
        return new ResponseEntity<>(chamadoRepository.findAll(), HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<ChamadoEntity> findById(int id) {
        Optional<ChamadoEntity> entity = chamadoRepository.findById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChamadoEntity chamado = entity.get();
        return new ResponseEntity<>(chamado,HttpStatus.OK);


    }

    public ResponseEntity<ChamadoEntity> createCalled(ChamadoDto dto) {
       ChamadoEntity chamado = new ChamadoEntity();
       chamado.setTitulo(dto.getTitulo());
       chamado.setNome(dto.getNome());
       chamado.setDescricao(dto.getDescricao());
       chamado.setDataCriacao(LocalDateTime.now());
       chamado.setStatus(dto.getStatus());
       chamado.setDataAtualizacao(dto.getDataAtualizacao());
       chamado.setAutorChamado(dto.getAutorChamado());
       ChamadoEntity salvo = chamadoRepository.save(chamado);

       return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }


    public ResponseEntity<ChamadoEntity>editCalled(int id, ChamadoDto dto) {
        Optional<ChamadoEntity> entity = chamadoRepository.findById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ChamadoEntity chamado = entity.get();
        chamado.setTitulo(dto.getTitulo());
        chamado.setNome(dto.getNome());
        chamado.setDescricao(dto.getDescricao());
        chamado.setDataCriacao(dto.getDataCriacao());
        chamado.setStatus(dto.getStatus());
        chamado.setDataAtualizacao(LocalDateTime.now());
        chamado.setAutorChamado(dto.getAutorChamado());
        ChamadoEntity salvo = chamadoRepository.save(chamado);

        return new ResponseEntity<>(salvo,HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteById(int id) {
       Optional<ChamadoEntity> chamado = chamadoRepository.findById(id);
       if (chamado.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       chamadoRepository.deleteById(id);
       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    public ResponseEntity<ChamadoEntity> resolverChamado(int id) {
        Optional<ChamadoEntity> chamado = chamadoRepository.findById(id);
        if (chamado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ChamadoEntity entity = chamado.get();
        entity.setStatus("Resolvido!");
        entity.setDataAtualizacao(LocalDateTime.now());
        ChamadoEntity salvo = chamadoRepository.save(entity);
        return new ResponseEntity<>(salvo,HttpStatus.OK);
    }
    
    
}

