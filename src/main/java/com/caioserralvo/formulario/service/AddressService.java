package com.caioserralvo.formulario.service;

import com.caioserralvo.formulario.client.ViaCepClient;
import com.caioserralvo.formulario.client.ViaCepResponse;
import com.caioserralvo.formulario.dto.AddressResponse;
import com.caioserralvo.formulario.exception.NotFoundException;
import com.caioserralvo.formulario.vo.ZipCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {
    private final ViaCepClient viaCepClient;

    public AddressService(ViaCepClient viaCepClient){
        this.viaCepClient = viaCepClient;
    }

    public AddressResponse getAddressByZipCode(ZipCode zipCode){
        ViaCepResponse response = viaCepClient.getAddress(zipCode.getValue());

        if(response == null || response.getErro() != null){
            throw new NotFoundException("CEP não encontrado");
        }

        return new AddressResponse(
                response.getLogradouro(),
                response.getBairro(),
                response.getLocalidade(),
                response.getUf(),
                response.getCep()
        );
    }
}
