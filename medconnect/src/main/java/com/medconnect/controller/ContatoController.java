package com.medconnect.controller;

import com.medconnect.dto.contato.CadastroContatoDto;
import com.medconnect.dto.contato.ResponseContatoDto;
import com.medconnect.model.Contato;
import com.medconnect.repository.ContatoRepository;
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
@RequestMapping("contatos")
public class ContatoController {
    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseContatoDto>> getAllContatos() {
        List<Contato> contatos = contatoRepository.findAll();
        List<ResponseContatoDto> responseContatos = new ArrayList<>();

        for (Contato contato : contatos) {
            responseContatos.add(new ResponseContatoDto(contato));
        }

        return ResponseEntity.ok(responseContatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContatoDto> getContatoById(@PathVariable Long id) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isPresent()) {
            Contato contato = optionalContato.get();
            return ResponseEntity.ok(new ResponseContatoDto(contato));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseContatoDto> criarContato(@RequestBody @Valid CadastroContatoDto contatoDto, UriComponentsBuilder uriBuilder) {
        Contato contato = new Contato(contatoDto);

        contatoRepository.save(contato);

        var uri = uriBuilder.path("contatos/{id}").buildAndExpand(contato.getIdContato()).toUri();

        return ResponseEntity.created(uri).body(new ResponseContatoDto(contato));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        contatoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
