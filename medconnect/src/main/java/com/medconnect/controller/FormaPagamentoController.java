package com.medconnect.controller;

import com.medconnect.dto.formaPagamento.AtualizacaoFormaPagamentoDto;
import com.medconnect.dto.formaPagamento.CadastroFormaPagamentoDto;
import com.medconnect.dto.formaPagamento.ResponseFormaPagamentoDto;
import com.medconnect.model.FormaPagamento;
import com.medconnect.repository.FormaPagamentoRepository;
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
@RequestMapping("formas-pagamento")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseFormaPagamentoDto>> getAllFormasPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        List<ResponseFormaPagamentoDto> responseFormasPagamento = new ArrayList<>();

        for (FormaPagamento formaPagamento : formasPagamento) {
            responseFormasPagamento.add(new ResponseFormaPagamentoDto(formaPagamento));
        }

        return ResponseEntity.ok(responseFormasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormaPagamentoDto> getFormaPagamentoById(@PathVariable Long id) {
        Optional<FormaPagamento> optionalFormaPagamento = formaPagamentoRepository.findById(id);

        if (optionalFormaPagamento.isPresent()) {
            FormaPagamento formaPagamento = optionalFormaPagamento.get();
            return ResponseEntity.ok(new ResponseFormaPagamentoDto(formaPagamento));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseFormaPagamentoDto> criarFormaPagamento(@RequestBody @Valid CadastroFormaPagamentoDto formaPagamentoDto, UriComponentsBuilder uriBuilder) {
        FormaPagamento formaPagamento = new FormaPagamento(formaPagamentoDto);

        formaPagamentoRepository.save(formaPagamento);

        var uri = uriBuilder.path("formas-pagamento/{id}").buildAndExpand(formaPagamento.getIdFormaPagamento()).toUri();

        return ResponseEntity.created(uri).body(new ResponseFormaPagamentoDto(formaPagamento));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        formaPagamentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseFormaPagamentoDto> put(@PathVariable("id")Long id,
                                                         @RequestBody @Valid AtualizacaoFormaPagamentoDto atualizacaoFormaPagamentoDto){
        FormaPagamento formaPagamento = formaPagamentoRepository.getReferenceById(id);
        formaPagamento.atualizarInformacoesFormaPagamento(atualizacaoFormaPagamentoDto);
        return ResponseEntity.ok(new ResponseFormaPagamentoDto(formaPagamento));
    }
}
