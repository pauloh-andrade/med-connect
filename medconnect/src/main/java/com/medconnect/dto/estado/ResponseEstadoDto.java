package com.medconnect.dto.estado;

import com.medconnect.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
public record ResponseEstadoDto(
        String sigla,

        String nome,

        LocalDateTime dataCadastro
) {
        public ResponseEstadoDto(Estado estado) {
                this(estado.getSigla(), estado.getNome(), estado.getDataCadastro());
        }
}
