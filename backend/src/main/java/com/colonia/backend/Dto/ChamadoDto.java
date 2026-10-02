package com.colonia.backend.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDto {
    private String titulo;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private String status;
    private LocalDateTime dataAtualizacao;
    private String autorChamado;

}
