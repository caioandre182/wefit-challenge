package com.caioserralvo.formulario.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Phone {
    @Column(name = "phone")
    private String value;

    protected Phone(){

    }

    public Phone(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }

        String digitsOnly = value.replaceAll("\\D", "");

        if (!isValid(digitsOnly)) {
            throw new IllegalArgumentException("Telefone fixo inválido.");
        }

        this.value = digitsOnly;
    }

    private boolean isValid(String telefone) {
        return telefone.matches("^\\d{2}[2-5]\\d{7}$");
    }

    public String format() {
        return value.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
