package com.medconnect.model;

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
@Table(name="t_mdct_produto")
@EntityListeners(AuditingEntityListener.class)
public class Produto {
    @Id
    @GeneratedValue
    @Column(name="id_produto")
    private Long idProduto;

    @Column(name="nm_produto", length = 80)
    private String nome;

    @Column(name="ds_produto", length = 100)
    private String descricao;

    @Column(name="qt_estoque")
    private Integer quantidadeEstoque;

    @Column(name="ct_produto", length = 80)
    private String categoria;

    @Column(name="vl_produto")
    private Double valor;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "produto")
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
