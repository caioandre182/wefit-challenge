package com.caioserralvo.formulario.controller;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.dto.CreateUserRequest;
import com.caioserralvo.formulario.dto.CreateUserResponse;
import com.caioserralvo.formulario.mapper.UserMapper;
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
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "Cadastrar formulário", description = "Endpoint para cadastrar um novo formulário com dados pessoais e endereço")
    @PostMapping
    public ResponseEntity<CreateUserResponse> send(@RequestBody @Valid CreateUserRequest request){
        User user = UserMapper.toDomain(request);

        log.info("Formulario recebido para o usuário: {}", user.getName());

        CreateUserResponse response = UserMapper.toResponse(user);

        return ResponseEntity.ok(response);
    }
}
