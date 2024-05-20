package com.medconnect.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoProdutoDto(
        @NotNull(message = "O nome do produto não pode ser nulo")
        @NotBlank(message = "O nome do produto não pode estar em branco")
        String nome,

        @NotBlank(message = "A descrição do produto não pode estar em branco")
        String descricao,

        @NotNull(message = "A quantidade em estoque do produto não pode ser nula")
        Integer quantidadeEstoque,

        @NotBlank(message = "A categoria do produto não pode estar em branco")
        String categoria,

        @NotNull(message = "O valor do produto não pode ser nulo")
        Double valor
) {}
