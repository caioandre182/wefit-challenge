package com.caioserralvo.formulario.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Cnpj {

    @Column(name = "cnpj")
    private String value;

    protected Cnpj(){

    }

    public Cnpj(String value) {
        String digitsOnly = value.replaceAll("\\D", "");

        if (digitsOnly.length() != 14 || !isValid(digitsOnly)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        this.value = digitsOnly;
    }

    private boolean isValid(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum1 = 0;
        for (int i = 0; i < 12; i++) {
            int dig = Character.getNumericValue(cnpj.charAt(i));
            sum1 += dig * weights1[i];
        }
        int checkDig1 = calculateCheckDigit(sum1);

        int sum2 = 0;
        for (int i = 0; i < 13; i++) {
            int digit = Character.getNumericValue(cnpj.charAt(i));
            sum2 += digit * weights2[i];
        }
        int checkDigit2 = calculateCheckDigit(sum2);

        return cnpj.endsWith("" + checkDig1 + checkDigit2);
    }

    private int calculateCheckDigit(int sum) {
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    private String formatCnpj(String cnpj) {
        return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    @Override
    public String toString() {
        return formatCnpj(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cnpj other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
