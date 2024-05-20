package com.medconnect.dto.cidade;

import com.medconnect.model.Cidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ResponseCidadeDto(
        String nome,

        Integer codigoIBGE,

        Integer ddd,

        LocalDateTime dataCadastro
) {

        public ResponseCidadeDto(Cidade cidade) {
                this(cidade.getNome(), cidade.getCodigoIBGE(), cidade.getDdd(), cidade.getDataCadastro());
        }
}