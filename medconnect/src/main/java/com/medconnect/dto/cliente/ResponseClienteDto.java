package com.medconnect.dto.cliente;

import com.medconnect.model.Cliente;
import com.medconnect.model.Contato;
import com.medconnect.model.Email;
import com.medconnect.model.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ResponseClienteDto(
        Long idCliente,
        String nome,
        Long cpf,
        String rg,
        String login,
        String senha,
        LocalDate dataNascimento,
        List<Email> emails,
        List<Contato> contatos,
        LocalDateTime dataCadastro
) {

    public ResponseClienteDto(Cliente cliente){
        this(cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getRg(), cliente.getLogin(), cliente.getSenha(), cliente.getDataNascimento(), cliente.getEmails(), cliente.getContatos(), cliente.getDataCadastro());
    }
}

