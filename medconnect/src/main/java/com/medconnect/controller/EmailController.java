package com.medconnect.controller;

import com.medconnect.dto.email.CadastroEmailDto;
import com.medconnect.dto.email.ResponseEmailDto;
import com.medconnect.model.Email;
import com.medconnect.repository.EmailRepository;
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
@RequestMapping("emails")
public class EmailController {
    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
    public ResponseEntity<List<ResponseEmailDto>> getAllEmails() {
        List<Email> emails = emailRepository.findAll();
        List<ResponseEmailDto> responseEmails = new ArrayList<>();

        for (Email email : emails) {
            responseEmails.add(new ResponseEmailDto(email));
        }

        return ResponseEntity.ok(responseEmails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmailDto> getEmailById(@PathVariable Long id) {
        Optional<Email> optionalEmail = emailRepository.findById(id);

        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            return ResponseEntity.ok(new ResponseEmailDto(email));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEmailDto> criarEmail(@RequestBody @Valid CadastroEmailDto emailDto, UriComponentsBuilder uriBuilder) {
        Email email = new Email(emailDto);

        emailRepository.save(email);

        var uri = uriBuilder.path("emails/{id}").buildAndExpand(email.getIdEmail()).toUri();

        return ResponseEntity.created(uri).body(new ResponseEmailDto(email));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        emailRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
