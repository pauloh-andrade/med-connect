package com.medconnect.dto.endereco;

import com.medconnect.model.Endereco;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ResponseEnderecoDto(
        int numeroLogradouro,

        String complementoNumero,

        String pontoReferencia,

        LocalDateTime dataCadastro
) {
    public ResponseEnderecoDto(Endereco endereco) {
        this(endereco.getNumeroLogradouro(), endereco.getComplementoNumero(), endereco.getPontoReferencia(), endereco.getDataCadastro());
    }
}
