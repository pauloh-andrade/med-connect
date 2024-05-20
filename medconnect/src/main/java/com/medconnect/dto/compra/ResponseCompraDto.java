package com.medconnect.dto.compra;

import com.medconnect.model.Cliente;
import com.medconnect.model.Compra;
import com.medconnect.model.Produto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseCompraDto(
        Integer quantidade,

        Double valor,

        String enderecoEntrega,

        LocalDateTime dataCadastro,

        Cliente cliente,

        Produto produto) {
    public ResponseCompraDto(Compra compra) {
        this(compra.getQuantidade(), compra.getValor(), compra.getEnderecoEntrega(), compra.getDataCadastro(), compra.getCliente(), compra.getProduto());
    }
}
