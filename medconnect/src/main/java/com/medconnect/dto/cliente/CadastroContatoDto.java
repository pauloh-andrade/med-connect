package com.medconnect.dto.cliente;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record CadastroContatoDto(int ddi, int ddd, Long telefone, LocalDateTime dataCadastro) {
}
