package com.medconnect.controller;

import com.medconnect.dto.estado.AtualizacaoEstadoDto;
import com.medconnect.dto.estado.CadastroEstadoDto;
import com.medconnect.dto.estado.ResponseEstadoDto;
import com.medconnect.model.Estado;
import com.medconnect.repository.EstadoRepository;
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
@RequestMapping("estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseEstadoDto>> getAllEstados() {
        List<Estado> estados = estadoRepository.findAll();
        List<ResponseEstadoDto> responseEstados = new ArrayList<>();

        for (Estado estado : estados) {
            responseEstados.add(new ResponseEstadoDto(estado));
        }

        return ResponseEntity.ok(responseEstados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEstadoDto> getEstadoById(@PathVariable Long id) {
        Optional<Estado> optionalEstado = estadoRepository.findById(id);

        if (optionalEstado.isPresent()) {
            Estado estado = optionalEstado.get();
            return ResponseEntity.ok(new ResponseEstadoDto(estado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEstadoDto> criarEstado(@RequestBody @Valid CadastroEstadoDto estadoDto, UriComponentsBuilder uriBuilder) {
        Estado estado = new Estado(estadoDto);

        estadoRepository.save(estado);

        var uri = uriBuilder.path("estados/{id}").buildAndExpand(estado.getIdEstado()).toUri();

        return ResponseEntity.created(uri).body(new ResponseEstadoDto(estado));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        estadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseEstadoDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoEstadoDto atualizacaoEstadoDto){
        Estado estado = estadoRepository.getReferenceById(id);
        estado.atualizarInformacoesEstado(atualizacaoEstadoDto);
        return ResponseEntity.ok(new ResponseEstadoDto(estado));
    }
}
