package com.medconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medconnect.dto.cliente.CadastroContatoDto;
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
@Table(name="t_mdct_contato")
@EntityListeners(AuditingEntityListener.class)
public class Contato {
    @Id
    @GeneratedValue
    @Column(name="id_contato")
    private Long idContato;

    @Column(name="nr_ddi")
    private int ddi;

    @Column(name="nr_ddd")
    private int ddd;

    @Column(name="nr_telefone")
    private Long telefone;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;

    public Contato(CadastroContatoDto contatoDto) {
        this.ddi = contatoDto.ddi();
        this.ddd = contatoDto.ddd();
        this.telefone = contatoDto.telefone();
        this.dataCadastro = contatoDto.dataCadastro();
    }
}
