package com.medconnect.controller;

import com.medconnect.dto.produto.AtualizacaoProdutoDto;
import com.medconnect.dto.produto.CadastroProdutoDto;
import com.medconnect.dto.produto.ResponseProdutoDto;
import com.medconnect.model.Produto;
import com.medconnect.repository.ProdutoRepository;
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
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ResponseProdutoDto>> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ResponseProdutoDto> responseProdutos = new ArrayList<>();

        for (Produto produto : produtos) {
            responseProdutos.add(new ResponseProdutoDto(produto));
        }

        return ResponseEntity.ok(responseProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProdutoDto> getProdutoById(@PathVariable Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            return ResponseEntity.ok(new ResponseProdutoDto(produto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseProdutoDto> criarProduto(@RequestBody @Valid CadastroProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
        Produto produto = new Produto(produtoDto);

        produtoRepository.save(produto);

        var uri = uriBuilder.path("produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri();

        return ResponseEntity.created(uri).body(new ResponseProdutoDto(produto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<ResponseProdutoDto> put(@PathVariable("id")Long id,
                                                  @RequestBody @Valid AtualizacaoProdutoDto atualizacaoProdutoDto){
        Produto produto = produtoRepository.getReferenceById(id);
        produto.atualizarInformacoesProduto(atualizacaoProdutoDto);
        return ResponseEntity.ok(new ResponseProdutoDto(produto));
    }
}
