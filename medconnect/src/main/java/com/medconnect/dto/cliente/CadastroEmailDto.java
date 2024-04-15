package com.medconnect.dto.cliente;

import com.medconnect.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record CadastroEmailDto(String email, LocalDateTime dataCadastro) {
}
