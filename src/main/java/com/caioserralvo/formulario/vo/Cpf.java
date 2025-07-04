package com.caioserralvo.formulario.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Cpf {
    @Column(name = "cpf")
    private String value;

    protected Cpf(){

    }

    public Cpf(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }

        String digitsOnly = value.replaceAll("\\D", "");

        if (digitsOnly.length() != 11 || !isValid(digitsOnly)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.value = digitsOnly;
    }

    @Override
    public String toString() {
        return formatCpf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpf other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private boolean isValid(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 9; i++) {
            int dig = Character.getNumericValue(cpf.charAt(i));
            if (dig < 0 || dig > 9) return false;
            sum1 += dig * (10 - i);
            sum2 += dig * (11 - i);
        }

        int checkDig1 = calculateCheckDigit(sum1);
        sum2 += checkDig1 * 2;
        int checkDig2 = calculateCheckDigit(sum2);

        return cpf.endsWith("" + checkDig1 + checkDig2);


    }

    private int calculateCheckDigit(int sum) {
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    private String formatCpf(String cpf) {
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
