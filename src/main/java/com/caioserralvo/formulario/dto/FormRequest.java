package com.caioserralvo.formulario.dto;

import com.caioserralvo.formulario.domain.Usuario;
import com.caioserralvo.formulario.enums.TipoPessoa;
import com.caioserralvo.formulario.enums.Uf;
import com.caioserralvo.formulario.vo.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FormRequest {

    @NotNull
    private TipoPessoa tipo;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    private String cpf;
    private String cnpj;

    @NotBlank
    private String telefone;

    @NotBlank
    private String celular;

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String numero;

    @NotBlank
    private String cidade;

    private String complemento;

    @NotBlank
    private String uf;

    public Usuario toUsuario() {
        if (tipo == TipoPessoa.FISICA && (cpf == null || cpf.isBlank())){
            throw new IllegalArgumentException("CPF é obrigatório para pessoa física");
        }

        if (tipo == TipoPessoa.JURIDICA && (cnpj == null || cnpj.isBlank())) {
            throw new IllegalArgumentException("CNPJ é obrigatório para pessoa jurídica");
        }

        if (tipo == TipoPessoa.JURIDICA && (cpf == null || cpf.isBlank())) {
            throw  new IllegalArgumentException("CPF do responsável é obrigatório para pessoa jurídica");
        }

        Cnpj cnpjVO = tipo == TipoPessoa.JURIDICA ? new Cnpj(cnpj) : null;

        return new Usuario(
                tipo,
                nome,
                new Email(email),
                new Cpf(cpf),
                cnpjVO,
                new Telefone(telefone),
                new Celular(celular),
                new Endereco(
                        new Cep(cep),
                        logradouro,
                        bairro,
                        numero,
                        complemento,
                        cidade,
                        Uf.valueOf(uf.toUpperCase())
                )
        );
    }
}
