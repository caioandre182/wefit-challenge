package com.caioserralvo.formulario.mapper;

import com.caioserralvo.formulario.domain.User;
import com.caioserralvo.formulario.dto.CreateUserRequest;
import com.caioserralvo.formulario.dto.CreateUserResponse;
import com.caioserralvo.formulario.enums.PersonType;
import com.caioserralvo.formulario.enums.StateCode;
import com.caioserralvo.formulario.vo.*;

public class UserMapper {
    public static User toDomain(CreateUserRequest request) {
        if (request.getType() == PersonType.FISICA && (request.getCpf() == null || request.getCpf().isBlank())){
            throw new IllegalArgumentException("CPF é obrigatório para pessoa física");
        }

        if (request.getType() == PersonType.JURIDICA && (request.getCnpj() == null || request.getCnpj().isBlank())) {
            throw new IllegalArgumentException("CNPJ é obrigatório para pessoa jurídica");
        }

        if (request.getType() == PersonType.JURIDICA && (request.getCpf()  == null || request.getCpf().isBlank())) {
            throw  new IllegalArgumentException("CPF do responsável é obrigatório para pessoa jurídica");
        }

        Cnpj cnpjVO = request.getType() == PersonType.JURIDICA ? new Cnpj(request.getCnpj()) : null;


        return new User(
                PersonType.valueOf(request.getType().toString().toUpperCase()),
                request.getName(),
                new Email(request.getEmail()),
                new Cpf(request.getCpf()),
                cnpjVO,
                new Phone(request.getPhone()),
                new CellPhone(request.getCellPhone()),
                new Address(
                        new ZipCode(request.getZipCode()),
                        request.getStreet(),
                        request.getDistrict(),
                        request.getNumber(),
                        request.getCity(),
                        request.getComplement(),
                        StateCode.valueOf(request.getStateCode().toUpperCase())
                )
        );
    }

    public static CreateUserResponse toResponse(User user) {
        return CreateUserResponse.builder()
                .type(user.getType())
                .name(user.getName())
                .email(user.getEmail().toString())
                .cpf(user.getCpf() != null ? user.getCpf().toString() : null)
                .cnpj(user.getCnpj() != null ? user.getCnpj().toString() : null)
                .phone(user.getPhone().toString())
                .cellPhone(user.getCellPhone().toString())
                .zipCode(user.getAddress().getZipCode().toString())
                .street(user.getAddress().getStreet())
                .district(user.getAddress().getDistrict())
                .number(user.getAddress().getNumber())
                .complement(user.getAddress().getComplement())
                .city(user.getAddress().getCity())
                .stateCode(user.getAddress().getStateCode().name())
                .build();
    }
}
