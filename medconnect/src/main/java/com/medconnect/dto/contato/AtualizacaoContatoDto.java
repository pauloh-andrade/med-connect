package com.medconnect.dto.contato;

import com.medconnect.model.Cliente;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AtualizacaoContatoDto(
        int ddi,
        int ddd,
        Long telefone,
        LocalDateTime dataCadastro,
        @NotNull(message = "O cliente não pode ser nulo") Cliente cliente) {
}
