package com.caioserralvo.formulario.domain;

import com.caioserralvo.formulario.enums.TipoPessoa;
import com.caioserralvo.formulario.vo.*;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Usuario {
    private final TipoPessoa tipo;
    private final String nome;
    private final Email email;
    private final Cpf cpf;
    private final Cnpj cnpj;
    private final Telefone telefone;
    private final Celular celular;
    private final Endereco endereco;

    public Usuario(TipoPessoa tipo, String nome, Email email, Cpf cpf, Cnpj cnpj, Telefone telefone, Celular celular, Endereco endereco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuário: ").append(nome)
                .append(", Tipo: ").append(tipo)
                .append(", Email: ").append(email)
                .append(", Telefone: ").append(telefone)
                .append(", Celular: ").append(celular)
                .append(", Endereço: ").append(endereco);

        if (tipo == TipoPessoa.FISICA) {
            sb.append(", CPF: ").append(cpf);
        } else if (tipo == TipoPessoa.JURIDICA) {
            sb.append(", CNPJ: ").append(cnpj)
                    .append(", CPF do Responsável: ").append(cpf);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario other)) return false;
        return nome.equals(other.nome)
                && email.equals(other.email)
                && cpf.equals(other.cpf)
                && cnpj.equals(other.cnpj)
                && telefone.equals(other.telefone)
                && celular.equals(other.celular)
                && endereco.equals(other.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, cpf, cnpj, telefone, celular, endereco);
    }
}
