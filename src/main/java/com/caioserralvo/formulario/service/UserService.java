package com.caioserralvo.formulario.service;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.exception.NotFoundException;
import com.caioserralvo.formulario.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User findByDocument(String document){
        String digits = document.replaceAll("\\D", "");

        if(digits.length() == 11) {
            return repository.findByCpf_Value(digits).orElseThrow(() -> new NotFoundException("CPF não encotrado na base de dados"));
        }

        if(digits.length() == 14){
            return repository.findByCnpj_Value(digits).orElseThrow(() -> new NotFoundException("CNPJ não encontrado na base de dados"));
        }

        throw new IllegalArgumentException("Documento inválido. Esperado CPF ou CNPJ");
    }
}
