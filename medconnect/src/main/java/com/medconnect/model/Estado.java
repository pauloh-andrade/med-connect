package com.medconnect.model;

import com.medconnect.dto.estado.AtualizacaoEstadoDto;
import com.medconnect.dto.estado.CadastroEstadoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="t_mdct_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {
    @Id
    @GeneratedValue
    @Column(name="id_estado")
    private Long idEstado;

    @Column(name="sg_estado", length = 2)
    private String sigla;

    @Column(name="nm_estado", length = 30)
    private String nome;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    public Estado(CadastroEstadoDto estadoDto) {
        this.sigla = estadoDto.sigla();
        this.nome = estadoDto.nome();
        this.dataCadastro = estadoDto.dataCadastro();
    }

    public void atualizarInformacoesEstado(AtualizacaoEstadoDto atualizacaoEstadoDto) {
        this.sigla = atualizacaoEstadoDto.sigla();
        this.nome = atualizacaoEstadoDto.nome();
        this.dataCadastro = atualizacaoEstadoDto.dataCadastro();
    }
}
