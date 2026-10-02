package com.colonia.backend.Database.Entitty;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoEntity {

    private Integer id;
    private String titulo;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private String status;
    private LocalDateTime dataAtualizacao;
    private String autorChamado;

}
