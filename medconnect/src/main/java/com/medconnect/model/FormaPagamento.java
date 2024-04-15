package com.medconnect.model;

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
@Table(name="t_mdct_forma_pagamento")
@EntityListeners(AuditingEntityListener.class)
public class FormaPagamento {
    @Id
    @GeneratedValue
    @Column(name="id_forma_pagamento")
    private Long idFormaPagamento;

    @Column(name="nm_forma_pagto", length = 80)
    private String nome;

    @Column(name="ds_forma_pagto", length = 80)
    private String descricao;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;
}
