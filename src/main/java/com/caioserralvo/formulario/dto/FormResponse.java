package com.caioserralvo.formulario.dto;

import com.caioserralvo.formulario.domain.Usuario;
import com.caioserralvo.formulario.enums.TipoPessoa;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FormResponse {
    private TipoPessoa tipo;
    private String nome;
    private String email;
    private String cpf;
    private String cnpj;
    private String telefone;
    private String celular;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cidade;
    private String complemento;
    private String uf;

    public static FormResponse fromUsuario(Usuario usuario) {
        return FormResponse.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail().toString())
                .cpf(usuario.getCpf().toString())
                .cnpj(usuario.getCnpj() != null ? usuario.getCnpj().toString() : null)
                .telefone(usuario.getTelefone().toString())
                .celular(usuario.getCelular().toString())
                .cep(usuario.getEndereco().getCep().toString())
                .logradouro(usuario.getEndereco().getLogradouro())
                .bairro(usuario.getEndereco().getBairro())
                .numero(usuario.getEndereco().getNumero())
                .complemento(usuario.getEndereco().getComplemento())
                .cidade(usuario.getEndereco().getCidade())
                .uf(usuario.getEndereco().getUf().name())
                .tipo(usuario.getTipo())
                .build();
    }
}
