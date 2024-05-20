package com.medconnect.dto.formaPagamento;

import com.medconnect.model.FormaPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record ResponseFormaPagamentoDto(
        String nome,

        String descricao,
        LocalDateTime dataCadastro
) {
        public ResponseFormaPagamentoDto(FormaPagamento formaPagamento) {
                this(formaPagamento.getNome(), formaPagamento.getDescricao(), formaPagamento.getDataCadastro());
        }
}
