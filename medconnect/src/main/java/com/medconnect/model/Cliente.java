package com.medconnect.model;

import com.medconnect.dto.cliente.CadastroClienteDto;
import com.medconnect.dto.cliente.CadastroContatoDto;
import com.medconnect.dto.cliente.CadastroEmailDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="t_mdct_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {
    @Id
    @GeneratedValue
    @Column(name="id_cliente")
    private Long idCliente;

    @Column(name="nm_cliente")
    private String nome;

    @Column(name="nr_cpf")
    private Long cpf;

    @Column(name="nm_rg")
    private String rg;

    @Column(name="lg_cliente")
    private String login;

    @Column(name="sh_cliente")
    private String senha;

    @Column(name="dt_nascimento")
    private LocalDate dataNascimento;

    @CreatedDate
    @Column(name="dt_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contato> contatos = new ArrayList<>();

    @OneToOne(mappedBy = "cliente")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras = new ArrayList<>();

    public Cliente(CadastroClienteDto clienteDto) {
        this.nome = clienteDto.nome();
        this.cpf = clienteDto.cpf();
        this.rg = clienteDto.rg();
        this.login = clienteDto.login();
        this.senha = clienteDto.senha();
        this.dataNascimento = clienteDto.dataNascimento();
        this.dataCadastro = clienteDto.dataCadastro();

        if (clienteDto.emails() != null) {
            this.emails = new ArrayList<>();
            for (CadastroEmailDto emailDto : clienteDto.emails()) {
                Email email = new Email(emailDto);
                email.setCliente(this);
                this.emails.add(email);
            }
        }

        if (clienteDto.contatos() != null) {
            this.contatos = new ArrayList<>();
            for (CadastroContatoDto contatoDto : clienteDto.contatos()) {
                Contato contato = new Contato(contatoDto);
                contato.setCliente(this);
                this.contatos.add(contato);
            }
        }
    }
}


