package com.caioserralvo.formulario.service;

import com.caioserralvo.formulario.domain.User;
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
}
