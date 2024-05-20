package com.medconnect.model;

import com.medconnect.dto.compra.CadastroCompraDto;
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
@Table(name="t_mdct_compra")
@EntityListeners(AuditingEntityListener.class)
public class Compra {
    @Id
    @GeneratedValue
    @Column(name="id_compra")
    private Long idCompra;

    @Column(name="qt_compra")
    private Integer quantidade;

    @Column(name="vl_compra")
    private Double valor;

    @Column(name="end_entrega", length = 100)
    private String enderecoEntrega;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;

    public Compra(CadastroCompraDto compraDto) {
        this.quantidade = compraDto.quantidade();
        this.valor = compraDto.valor();
        this.enderecoEntrega = compraDto.enderecoEntrega();
        this.dataCadastro = compraDto.dataCadastro();
        this.cliente = compraDto.cliente();
        this.produto = compraDto.produto();
    }
}

