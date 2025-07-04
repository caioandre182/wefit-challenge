package com.caioserralvo.formulario.controller;

import com.caioserralvo.formulario.dto.AddressResponse;
import com.caioserralvo.formulario.service.AddressService;
import com.caioserralvo.formulario.vo.ZipCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service){
        this.service = service;
    }

    @Operation(
            summary = "Consultar endereço por CEP",
            description = "Retorna o endereço correspondente a um CEP válido utilizando a integração com o serviço ViaCEP."
    )
    @GetMapping("/{zipCode}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable String zipCode){
        ZipCode validatedZipCode = new ZipCode(zipCode);

        AddressResponse response = service.getAddressByZipCode(validatedZipCode);
        return ResponseEntity.ok(response);
    }
}
