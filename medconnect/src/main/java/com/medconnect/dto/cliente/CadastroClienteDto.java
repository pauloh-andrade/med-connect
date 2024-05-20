package com.medconnect.dto.cliente;

import com.medconnect.model.Contato;
import com.medconnect.model.Email;
import com.medconnect.model.Endereco;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CadastroClienteDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 150, message = "A descrição deve ter no máximo 150 caracteres")
        String nome,

        @NotNull(message = "O CPF não pode ser nulo")
        Long cpf,
        @NotBlank(message = "O RG não pode ser nulo")
        @Size(max = 10, message = "Deve ter no máximo 10 caracteres")
        String rg,
        @NotBlank(message = "O login não pode estar em branco")
        @Size(max = 30, message = "Deve ter no máximo 30 caracteres")
        String login,
        @NotBlank(message = "O login não pode estar em branco")
        @Size(max = 30, message = "Deve ter no máximo 30 caracteres")
        String senha,
        @NotNull(message = "A data não pode ser nula")
        LocalDate dataNascimento,
        @NotNull
        List<CadastroEmailDto> emails,
        @NotNull
        List<CadastroContatoDto> contatos,
        @NotNull
        LocalDateTime dataCadastro) {
}
