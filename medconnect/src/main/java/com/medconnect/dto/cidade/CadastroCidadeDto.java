package com.medconnect.dto.cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CadastroCidadeDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 60, message = "O nome da cidade deve ter no máximo 60 caracteres")
        String nome,

        @NotNull(message = "O código IBGE não pode ser nulo")
        Integer codigoIBGE,

        @NotNull(message = "O DDD não pode ser nulo")
        Integer ddd,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro
) {

}