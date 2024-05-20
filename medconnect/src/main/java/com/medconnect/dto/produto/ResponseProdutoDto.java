package com.medconnect.dto.produto;

import com.medconnect.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseProdutoDto(
        String nome,

        String descricao,

        Integer quantidadeEstoque,

        String categoria,

        Double valor
) {
        public ResponseProdutoDto(Produto produto) {
                this(produto.getNome(), produto.getDescricao(), produto.getQuantidadeEstoque(), produto.getCategoria(), produto.getValor());
        }
}
