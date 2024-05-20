package com.medconnect.model;

import com.medconnect.dto.bairro.CadastroBairroDto;
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
@Table(name="t_mdct_bairro")
@EntityListeners(AuditingEntityListener.class)
public class Bairro {
    @Id
    @GeneratedValue
    @Column(name="id_bairro")
    private Long idBairro;

    @Column(name="nm_bairro", length = 45)
    private String nome;

    @Column(name="nm_zona_bairro", length = 20)
    private String zona;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "bairro")
    private List<Logradouro> logradouros = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="id_cidade")
    private Cidade cidade;

    public Bairro(CadastroBairroDto bairroDto) {
        this.nome = bairroDto.nome();
        this.zona = bairroDto.zona();
        this.dataCadastro = bairroDto.dataCadastro();
    }
}
