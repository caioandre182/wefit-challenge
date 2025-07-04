package com.caioserralvo.formulario.vo;

import com.caioserralvo.formulario.enums.StateCode;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Address {

    @Embedded
    private ZipCode zipCode;
    private String street;
    private String district;
    private String number;
    private String complement;
    private String city;

    @Enumerated(EnumType.STRING)
    private StateCode stateCode;

    protected Address(){

    }

    public Address(ZipCode zipCode, String street, String district,
                   String number, String complement, String city, StateCode stateCode){
        if (zipCode == null || street == null || district == null || number == null || city == null || stateCode == null) {
            throw new IllegalArgumentException("Endereço incompleto");
        }

        this.zipCode = zipCode;
        this.street = street;
        this.district = district;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s, %s - %s - %s, %s",
                street, number, district, zipCode.format(), city, stateCode, complement != null ? complement : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address other)) return false;
        return zipCode.equals(other.zipCode)
                && street.equals(other.street)
                && district.equals(other.district)
                && number.equals(other.number)
                && city.equals(other.city)
                && Objects.equals(complement, other.complement)
                && stateCode == other.stateCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, street, district, number, city, complement, stateCode);
    }

}
