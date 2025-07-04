package com.caioserralvo.formulario.domain;

import com.caioserralvo.formulario.enums.PersonType;
import com.caioserralvo.formulario.vo.*;
import lombok.Getter;

import java.util.Objects;

@Getter
public class User {
    private final PersonType type;
    private final String name;
    private final Email email;
    private final Cpf cpf;
    private final Cnpj cnpj;
    private final Phone phone;
    private final CellPhone cellPhone;
    private final Address address;

    public User(PersonType type, String nome, Email email, Cpf cpf, Cnpj cnpj, Phone phone, CellPhone cellPhone, Address address) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        this.type = type;
        this.name = nome;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuário: ").append(name)
                .append(", Tipo: ").append(type)
                .append(", Email: ").append(email)
                .append(", Telefone: ").append(phone)
                .append(", Celular: ").append(cellPhone)
                .append(", Endereço: ").append(address);

        if (type == PersonType.FISICA) {
            sb.append(", CPF: ").append(cpf);
        } else if (type == PersonType.JURIDICA) {
            sb.append(", CNPJ: ").append(cnpj)
                    .append(", CPF do Responsável: ").append(cpf);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User other)) return false;
        return name.equals(other.name)
                && email.equals(other.email)
                && cpf.equals(other.cpf)
                && cnpj.equals(other.cnpj)
                && phone.equals(other.phone)
                && cellPhone.equals(other.cellPhone)
                && address.equals(other.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, cpf, cnpj, phone, cellPhone, address);
    }
}
