package com.medconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medconnect.dto.email.CadastroEmailDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="t_mdct_email")
@EntityListeners(AuditingEntityListener.class)
public class Email {
    @Id
    @GeneratedValue
    @Column(name="id_email")
    private Long idEmail;

    @Column(name="ds_email", length = 80)
    private String email;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;

    public Email(com.medconnect.dto.cliente.CadastroEmailDto emailDto) {
        this.email = emailDto.email();
        this.dataCadastro = emailDto.dataCadastro();
    }

    public Email(CadastroEmailDto emailDto) {
        this.email = emailDto.email();
        this.dataCadastro = emailDto.dataCadastro();
    }
}
