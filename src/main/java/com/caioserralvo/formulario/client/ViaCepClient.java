package com.caioserralvo.formulario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCEP", url = "${viacep.api.url}")
public interface ViaCepClient {

    @GetMapping("/{zipCode}/json/")
    ViaCepResponse getAddress(@PathVariable("zipCode") String zipCode);
}
