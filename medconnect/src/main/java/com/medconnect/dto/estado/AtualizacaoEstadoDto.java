package com.medconnect.dto.estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
public record AtualizacaoEstadoDto(
        @NotBlank(message = "A sigla do estado não pode estar em branco")
        @Size(min = 2, max = 2, message = "A sigla do estado deve ter 2 caracteres")
        String sigla,
        @NotBlank(message = "O nome do estado não pode estar em branco")
        @Size(max = 30, message = "O nome do estado deve ter no máximo 30 caracteres")
        String nome,
        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro
) {}
