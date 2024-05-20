package com.medconnect.dto.bairro;

import com.medconnect.model.Bairro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ResponseBairroDto(
        String nome,

        String zona,

        LocalDateTime dataCadastro
) {
    public ResponseBairroDto(Bairro bairro) {
        this(bairro.getNome(), bairro.getZona(), bairro.getDataCadastro());
    }
}
