package com.medconnect.dto.formaPagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record CadastroFormaPagamentoDto(
        Long idFormaPagamento,
        @NotBlank(message = "O nome da forma de pagamento não pode estar em branco")
        @Size(max = 80, message = "O nome da forma de pagamento deve ter no máximo 80 caracteres")
        String nome,
        @Size(max = 80, message = "A descrição da forma de pagamento deve ter no máximo 80 caracteres")
        String descricao,
        LocalDateTime dataCadastro
) {}
