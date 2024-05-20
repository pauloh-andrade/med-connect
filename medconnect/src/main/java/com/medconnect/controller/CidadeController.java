package com.medconnect.controller;

import com.medconnect.dto.cidade.CadastroCidadeDto;
import com.medconnect.dto.cidade.ResponseCidadeDto;
import com.medconnect.model.Cidade;
import com.medconnect.repository.CidadeRepository;
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
@RequestMapping("cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public ResponseEntity<List<ResponseCidadeDto>> getAllCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();
        List<ResponseCidadeDto> responseCidades = new ArrayList<>();

        for (Cidade cidade : cidades) {
            responseCidades.add(new ResponseCidadeDto(cidade));
        }

        return ResponseEntity.ok(responseCidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCidadeDto> getCidadeById(@PathVariable Long id) {
        Optional<Cidade> optionalCidade = cidadeRepository.findById(id);

        if (optionalCidade.isPresent()) {
            Cidade cidade = optionalCidade.get();
            return ResponseEntity.ok(new ResponseCidadeDto(cidade));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCidadeDto> criarCidade(@RequestBody @Valid CadastroCidadeDto cidadeDto, UriComponentsBuilder uriBuilder) {
        Cidade cidade = new Cidade(cidadeDto);

        cidadeRepository.save(cidade);

        var uri = uriBuilder.path("cidades/{id}").buildAndExpand(cidade.getIdCidade()).toUri();

        return ResponseEntity.created(uri).body(new ResponseCidadeDto(cidade));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        cidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
