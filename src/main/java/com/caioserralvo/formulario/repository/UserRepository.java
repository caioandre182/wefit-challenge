package com.caioserralvo.formulario.repository;

import com.caioserralvo.formulario.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf_Value(String cpf);
    Optional<User> findByCnpj_Value(String cnpj);
}
