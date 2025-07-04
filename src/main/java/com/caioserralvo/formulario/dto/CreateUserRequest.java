package com.caioserralvo.formulario.dto;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.enums.PersonType;
import com.caioserralvo.formulario.enums.StateCode;
import com.caioserralvo.formulario.vo.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotNull
    private PersonType type;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String cpf;

    private String cnpj;

    @NotBlank
    private String phone;

    @NotBlank
    private String cellPhone;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @NotBlank
    private String district;

    @NotBlank
    private String number;

    @NotBlank
    private String city;

    private String complement;

    @NotBlank
    private String stateCode;
}
