package com.medconnect.dto.compra;

import com.medconnect.model.Cliente;
import com.medconnect.model.Produto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroCompraDto(
        @NotNull(message = "A quantidade não pode ser nula")
        Integer quantidade,

        @NotNull(message = "O valor não pode ser nulo")
        Double valor,

        String enderecoEntrega,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDateTime dataCadastro,

        @NotNull(message = "O cliente não pode ser nulo")
        Cliente cliente,

        @NotNull(message = "O produto não pode ser nulo")
        Produto produto) {
}
