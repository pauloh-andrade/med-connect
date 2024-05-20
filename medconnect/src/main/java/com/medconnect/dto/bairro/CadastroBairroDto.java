package com.medconnect.dto.bairro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CadastroBairroDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 45, message = "O nome do bairro deve ter no máximo 45 caracteres")
        String nome,

        @NotBlank(message = "A zona não pode estar em branco")
        @Size(max = 20, message = "A zona do bairro deve ter no máximo 20 caracteres")
        String zona,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro
) {
}
