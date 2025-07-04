package com.caioserralvo.formulario.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class CellPhone {

    @Column(name = "cellPhone")
    private String value;

    protected CellPhone(){

    }

    public CellPhone(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Celular não pode ser nulo ou vazio.");
        }

        String digitsOnly = value.replaceAll("\\D", "");

        if (!isValid(digitsOnly)) {
            throw new IllegalArgumentException("Celular inválido.");
        }

        this.value = digitsOnly;
    }

    private boolean isValid(String celular) {
        return celular.matches("^\\d{2}9\\d{8}$");
    }

    public String format() {
        return value.replaceFirst("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2$3-$4");
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CellPhone other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
