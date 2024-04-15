package com.medconnect.dto.cliente;

import com.medconnect.model.Contato;
import com.medconnect.model.Email;
import com.medconnect.model.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CadastroClienteDto(
        @NotBlank
        String nome,
        @NotNull
        Long cpf,
        @NotBlank
        String rg,
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        List<CadastroEmailDto> emails,
        @NotNull
        List<CadastroContatoDto> contatos,
        @NotNull
        LocalDateTime dataCadastro) {
}
