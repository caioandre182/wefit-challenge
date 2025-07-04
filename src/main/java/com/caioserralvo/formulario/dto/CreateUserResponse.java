package com.caioserralvo.formulario.dto;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.enums.PersonType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserResponse {
    private PersonType type;
    private String name;
    private String email;
    private String cpf;
    private String cnpj;
    private String phone;
    private String cellPhone;
    private String zipCode;
    private String street;
    private String district;
    private String number;
    private String city;
    private String complement;
    private String stateCode;
}
