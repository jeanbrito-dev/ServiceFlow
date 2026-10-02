package com.colonia.backend.Service;

import com.colonia.backend.Database.Entitty.ChamadoEntity;
import com.colonia.backend.Dto.ChamadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ChamadoService {

    private static final ArrayList<ChamadoEntity> CHAMADOS = new ArrayList<ChamadoEntity>();

    static {
        CHAMADOS.add(ChamadoEntity.builder()
                .id(1)
                .titulo("Chamado Inicial")
                .nome("Gabriel")
                .descricao("Primeiro chamado para teste!!!")
                //espera receber YYYY-MM-DDTHH:mm:ss
                .dataCriacao(LocalDateTime.of(2026,10,2,14,30,0))
                .status("Pendente")
                .dataAtualizacao(LocalDateTime.of(2026,10,2,14,30,0))
                .autorChamado("Jean")
                .build()
        );
    }

    public int createId() {
        return CHAMADOS.stream().mapToInt(ChamadoEntity::getId).max().orElse(0)+1;
    }

    public ResponseEntity<ArrayList<ChamadoEntity>> findAll() {
        return new ResponseEntity<>(CHAMADOS, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<ChamadoEntity> findById(int id) {
        ChamadoEntity target = CHAMADOS.stream().filter(chamado -> chamado.getId().equals(id)).findFirst().orElse(null);
        return new ResponseEntity<>(target,HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<ChamadoEntity> createCalled(ChamadoDto dto) {
    ChamadoEntity chamado = ChamadoEntity.builder().id(createId()).titulo((dto.getTitulo())).nome(dto.getNome()).descricao(dto.getDescricao()).dataCriacao(dto.getDataCriacao()).status(dto.getStatus()).dataAtualizacao(dto.getDataAtualizacao()).autorChamado((dto.getAutorChamado())).build();
    CHAMADOS.add(chamado);
    return new ResponseEntity<>(chamado,HttpStatusCode.valueOf(201));
    }




}
