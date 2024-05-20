package com.medconnect.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtualizacaoEnderecoDto(
        @Min(value = 1, message = "O número do logradouro deve ser maior que zero")
        int numeroLogradouro,

        @NotBlank()
        @Size(max = 30, message = "O complemento do número deve ter no máximo 30 caracteres")
        String complementoNumero,

        @NotBlank()
        @Size(max = 50, message = "O ponto de referência deve ter no máximo 50 caracteres")
        String pontoReferencia,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro
) {
}
