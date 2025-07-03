package com.caioserralvo.formulario.vo;

import com.caioserralvo.formulario.enums.Uf;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Endereco {
    private final Cep cep;
    private final String logradouro;
    private final String bairro;
    private final String numero;
    private final String complemento;
    private final String cidade;
    private final Uf uf;

    public Endereco(Cep cep, String logradouro, String bairro,
                    String numero, String complemento, String cidade, Uf uf){
        if (cep == null || logradouro == null || bairro == null || numero == null || cidade == null || uf == null) {
            throw new IllegalArgumentException("Endereço incompleto");
        }

        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s, %s - %s - %s, %s",
                logradouro, numero, bairro, cep.format(), cidade, uf, complemento != null ? complemento : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco other)) return false;
        return cep.equals(other.cep)
                && logradouro.equals(other.logradouro)
                && bairro.equals(other.bairro)
                && numero.equals(other.numero)
                && cidade.equals(other.cidade)
                && Objects.equals(complemento, other.complemento)
                && uf == other.uf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, bairro, numero, cidade, complemento, uf);
    }

}
