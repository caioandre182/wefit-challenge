package com.caioserralvo.formulario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {
    private String street;
    private String district;
    private String city;
    private String state;
    private String zipCode;
}
