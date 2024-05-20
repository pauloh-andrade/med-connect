package com.medconnect.model;

import com.medconnect.dto.logradouro.AtualizacaoLogradouroDto;
import com.medconnect.dto.logradouro.CadastroLogradouroDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="t_mdct_logradouro")
@EntityListeners(AuditingEntityListener.class)
public class Logradouro {
    @Id
    @GeneratedValue
    @Column(name="id_logradouro")
    private Long idLogradouro;

    @Column(name="nm_logradouro", length = 100)
    private String nome;

    @Column(name="nr_cep")
    private int cep;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToOne
    @JoinColumn(name="id_endereco_cliente")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name="id_bairro")
    private Bairro bairro;

    public Logradouro(CadastroLogradouroDto logradouroDto) {
        this.nome = logradouroDto.nome();
        this.cep = logradouroDto.cep();
        this.dataCadastro = logradouroDto.dataCadastro();
    }

    public void atualizarInformacoesLogradouro(AtualizacaoLogradouroDto atualizacaoLogradouroDto) {
        this.nome = atualizacaoLogradouroDto.nome();
        this.cep = atualizacaoLogradouroDto.cep();
        this.dataCadastro = atualizacaoLogradouroDto.dataCadastro();
    }
}
