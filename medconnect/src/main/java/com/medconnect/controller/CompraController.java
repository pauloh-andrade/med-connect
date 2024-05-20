package com.medconnect.controller;

import com.medconnect.dto.compra.CadastroCompraDto;
import com.medconnect.dto.compra.ResponseCompraDto;
import com.medconnect.model.Compra;
import com.medconnect.repository.CompraRepository;
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
@RequestMapping("compras")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public ResponseEntity<List<ResponseCompraDto>> getAllCompras() {
        List<Compra> compras = compraRepository.findAll();
        List<ResponseCompraDto> responseCompras = new ArrayList<>();

        for (Compra compra : compras) {
            responseCompras.add(new ResponseCompraDto(compra));
        }

        return ResponseEntity.ok(responseCompras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompraDto> getCompraById(@PathVariable Long id) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);

        if (optionalCompra.isPresent()) {
            Compra compra = optionalCompra.get();
            return ResponseEntity.ok(new ResponseCompraDto(compra));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCompraDto> criarCompra(@RequestBody @Valid CadastroCompraDto compraDto, UriComponentsBuilder uriBuilder) {
        Compra compra = new Compra(compraDto);

        compraRepository.save(compra);

        var uri = uriBuilder.path("compras/{id}").buildAndExpand(compra.getIdCompra()).toUri();

        return ResponseEntity.created(uri).body(new ResponseCompraDto(compra));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        compraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
