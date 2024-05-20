package com.medconnect.dto.email;

import com.medconnect.model.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseEmailDto(
        String email,

        LocalDateTime dataCadastro
) {

        public ResponseEmailDto(Email email) {
                this(email.getEmail(), email.getDataCadastro());
        }
}
