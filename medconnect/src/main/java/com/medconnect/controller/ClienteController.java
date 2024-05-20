package com.medconnect.controller;

import com.medconnect.dto.cliente.AtualizacaoClienteDto;
import com.medconnect.dto.cliente.CadastroClienteDto;
import com.medconnect.dto.cliente.ResponseClienteDto;
import com.medconnect.model.Cliente;
import com.medconnect.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<ResponseClienteDto>> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ResponseClienteDto> responseClientes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            responseClientes.add(new ResponseClienteDto(cliente));
        }

        return ResponseEntity.ok(responseClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClienteDto> getClienteById(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            return ResponseEntity.ok(new ResponseClienteDto(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseClienteDto> criarCliente(@RequestBody @Valid CadastroClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
        Cliente cliente = new Cliente(clienteDto);

        clienteRepository.save(cliente);

        var uri = uriBuilder.path("clientes/{id}").buildAndExpand(cliente.getIdCliente()).toUri();

        return ResponseEntity.created(uri).body(new ResponseClienteDto(cliente));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseClienteDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoClienteDto atualizacaoClienteDto){
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.atualizarInformacoesCliente(atualizacaoClienteDto);
        return ResponseEntity.ok(new ResponseClienteDto(cliente));
    }
}
