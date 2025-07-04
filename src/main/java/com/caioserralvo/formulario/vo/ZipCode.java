package com.caioserralvo.formulario.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ZipCode {
    private final String value;

    public ZipCode(String value){
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException("CEP não pode ser vazio ou nulo");
        }

        String digitsOnly = value.replaceAll("\\D", "");

        if (!isValid(digitsOnly)) {
            throw new IllegalArgumentException("CEP inválido.");
        }

        this.value = digitsOnly;
    }

    private boolean isValid(String cep) {
        return cep.matches("^\\d{8}$");
    }

    public String format() {
        return value.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZipCode other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
