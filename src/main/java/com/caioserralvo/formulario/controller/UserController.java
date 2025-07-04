package com.caioserralvo.formulario.controller;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.dto.CreateUserRequest;
import com.caioserralvo.formulario.dto.CreateUserResponse;
import com.caioserralvo.formulario.mapper.UserMapper;
import com.caioserralvo.formulario.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Cadastrar formulário", description = "Endpoint para cadastrar um novo formulário com dados pessoais e endereço")
    @PostMapping
    public ResponseEntity<CreateUserResponse> send(@RequestBody @Valid CreateUserRequest request){
        User user = UserMapper.toDomain(request);

        User savedUser = service.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(savedUser));
    }
}
