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
@Table(name="t_mdct_endereco_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Endereco {
    @Id
    @GeneratedValue
    @Column(name="id_endereco_cliente")
    private Long idEnderecoCliente;

    @Column(name="nr_logradouro")
    private int numeroLogradouro;

    @Column(name="ds_complemento_numero", length = 30)
    private String complementoNumero;

    @Column(name="ds_ponto_referencia", length = 50)
    private String pontoReferencia;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne(mappedBy = "endereco")
    private Logradouro logradouro;
}
