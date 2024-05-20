package com.medconnect.controller;

import com.medconnect.dto.bairro.CadastroBairroDto;
import com.medconnect.dto.bairro.ResponseBairroDto;
import com.medconnect.model.Bairro;
import com.medconnect.repository.BairroRepository;
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
@RequestMapping("bairros")
public class BairroController {
    @Autowired
    private BairroRepository bairroRepository;

    @GetMapping
    public ResponseEntity<List<ResponseBairroDto>> getAllBairros() {
        List<Bairro> bairros = bairroRepository.findAll();
        List<ResponseBairroDto> responseBairros = new ArrayList<>();

        for (Bairro bairro : bairros) {
            responseBairros.add(new ResponseBairroDto(bairro));
        }

        return ResponseEntity.ok(responseBairros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBairroDto> getBairroById(@PathVariable Long id) {
        Optional<Bairro> optionalBairro = bairroRepository.findById(id);

        if (optionalBairro.isPresent()) {
            Bairro bairro = optionalBairro.get();
            return ResponseEntity.ok(new ResponseBairroDto(bairro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseBairroDto> criarBairro(@RequestBody @Valid CadastroBairroDto bairroDto, UriComponentsBuilder uriBuilder) {
        Bairro bairro = new Bairro(bairroDto);


        bairroRepository.save(bairro);

        var uri = uriBuilder.path("bairros/{id}").buildAndExpand(bairro.getIdBairro()).toUri();

        return ResponseEntity.created(uri).body(new ResponseBairroDto(bairro));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        bairroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
