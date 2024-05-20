package com.medconnect.dto.contato;

import com.medconnect.model.Contato;
import java.time.LocalDateTime;

public record ResponseContatoDto(
        int ddi,
        int ddd,
        Long telefone,
        LocalDateTime dataCadastro ) {

    public ResponseContatoDto(Contato contato) {
        this(contato.getDdi(), contato.getDdd(), contato.getTelefone(), contato.getDataCadastro());
    }
}
