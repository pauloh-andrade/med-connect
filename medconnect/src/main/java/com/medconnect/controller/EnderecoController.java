package com.medconnect.controller;

import com.medconnect.dto.endereco.AtualizacaoEnderecoDto;
import com.medconnect.dto.endereco.CadastroEnderecoDto;
import com.medconnect.dto.endereco.ResponseEnderecoDto;
import com.medconnect.model.Endereco;
import com.medconnect.repository.EnderecoRepository;
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
@RequestMapping("enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseEnderecoDto>> getAllEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<ResponseEnderecoDto> responseEnderecos = new ArrayList<>();

        for (Endereco endereco : enderecos) {
            responseEnderecos.add(new ResponseEnderecoDto(endereco));
        }

        return ResponseEntity.ok(responseEnderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDto> getEnderecoById(@PathVariable Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);

        if (optionalEndereco.isPresent()) {
            Endereco endereco = optionalEndereco.get();
            return ResponseEntity.ok(new ResponseEnderecoDto(endereco));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEnderecoDto> criarEndereco(@RequestBody @Valid CadastroEnderecoDto enderecoDto, UriComponentsBuilder uriBuilder) {
        Endereco endereco = new Endereco(enderecoDto);

        enderecoRepository.save(endereco);

        var uri = uriBuilder.path("enderecos/{id}").buildAndExpand(endereco.getIdEnderecoCliente()).toUri();

        return ResponseEntity.created(uri).body(new ResponseEnderecoDto(endereco));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        enderecoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseEnderecoDto> put(@PathVariable("id")Long id,
                                                   @RequestBody @Valid AtualizacaoEnderecoDto atualizacaoEnderecoDto){
        Endereco endereco = enderecoRepository.getReferenceById(id);
        endereco.atualizarInformacoesEndereco(atualizacaoEnderecoDto);
        return ResponseEntity.ok(new ResponseEnderecoDto(endereco));
    }
}
