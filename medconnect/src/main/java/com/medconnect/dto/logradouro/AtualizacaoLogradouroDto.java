package com.medconnect.dto.logradouro;

import com.medconnect.model.Bairro;
import com.medconnect.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtualizacaoLogradouroDto(
        @NotBlank(message = "O nome do logradouro não pode estar em branco")
        @Size(max = 100, message = "O nome do logradouro deve ter no máximo 100 caracteres")
        String nome,
        int cep,
        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro,
        Endereco endereco,
        Bairro bairro
) {}
