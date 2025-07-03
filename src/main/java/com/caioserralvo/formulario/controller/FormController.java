package com.caioserralvo.formulario.controller;

import com.caioserralvo.formulario.domain.Usuario;
import com.caioserralvo.formulario.dto.FormRequest;
import com.caioserralvo.formulario.dto.FormResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/formulario")
public class FormController {

    @Operation(summary = "Cadastrar formulário", description = "Endpoint para cadastrar um novo formulário com dados pessoais e endereço")
    @PostMapping
    public ResponseEntity<FormResponse> send(@RequestBody @Valid FormRequest request){
        Usuario usuario = request.toUsuario();

        log.info("Formulario recebido para o usuário: {}", usuario.getNome());

        FormResponse response = FormResponse.fromUsuario(usuario);

        return ResponseEntity.ok(response);
    }
}
