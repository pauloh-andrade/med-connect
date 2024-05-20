package com.medconnect.controller;

import com.medconnect.dto.logradouro.AtualizacaoLogradouroDto;
import com.medconnect.dto.logradouro.CadastroLogradouroDto;
import com.medconnect.dto.logradouro.ResponseLogradouroDto;
import com.medconnect.model.Logradouro;
import com.medconnect.repository.LogradouroRepository;
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
@RequestMapping("logradouros")
public class LogradouroController {
    @Autowired
    private LogradouroRepository logradouroRepository;

    @GetMapping
    public ResponseEntity<List<ResponseLogradouroDto>> getAllLogradouros() {
        List<Logradouro> logradouros = logradouroRepository.findAll();
        List<ResponseLogradouroDto> responseLogradouros = new ArrayList<>();

        for (Logradouro logradouro : logradouros) {
            responseLogradouros.add(new ResponseLogradouroDto(logradouro));
        }

        return ResponseEntity.ok(responseLogradouros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLogradouroDto> getLogradouroById(@PathVariable Long id) {
        Optional<Logradouro> optionalLogradouro = logradouroRepository.findById(id);

        if (optionalLogradouro.isPresent()) {
            Logradouro logradouro = optionalLogradouro.get();
            return ResponseEntity.ok(new ResponseLogradouroDto(logradouro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseLogradouroDto> criarLogradouro(@RequestBody @Valid CadastroLogradouroDto logradouroDto, UriComponentsBuilder uriBuilder) {
        Logradouro logradouro = new Logradouro(logradouroDto);

        logradouroRepository.save(logradouro);

        var uri = uriBuilder.path("logradouros/{id}").buildAndExpand(logradouro.getIdLogradouro()).toUri();

        return ResponseEntity.created(uri).body(new ResponseLogradouroDto(logradouro));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        logradouroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseLogradouroDto> put(@PathVariable("id")Long id,
                                                     @RequestBody @Valid AtualizacaoLogradouroDto atualizacaoLogradouroDto){
        Logradouro logradouro = logradouroRepository.getReferenceById(id);
        logradouro.atualizarInformacoesLogradouro(atualizacaoLogradouroDto);
        return ResponseEntity.ok(new ResponseLogradouroDto(logradouro));
    }
}
