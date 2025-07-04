package com.caioserralvo.formulario.controller;

import com.caioserralvo.formulario.dto.AddressResponse;
import com.caioserralvo.formulario.exception.NotFoundException;
import com.caioserralvo.formulario.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService service;

    @Test
    public void shouldReturnAddress_whenZipCodeIsValid() throws Exception {
        String zipCode = "05001-200";
        AddressResponse mockResponse = new AddressResponse(
                "Avenida Francisco Matarazzo", "Água Branca", "São Paulo", "SP", "05001-200"
        );

        when(service.getAddressByZipCode(new com.caioserralvo.formulario.vo.ZipCode(zipCode)))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/address/" + zipCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.street").value("Avenida Francisco Matarazzo"))
                .andExpect(jsonPath("$.district").value("Água Branca"))
                .andExpect(jsonPath("$.city").value("São Paulo"))
                .andExpect(jsonPath("$.state").value("SP"))
                .andExpect(jsonPath("$.zipCode").value(zipCode));
    }

    @Test
    public void shouldReturn404_whenZipCodeNotFound() throws Exception {
        String zipCode = "08295-006";

        when(service.getAddressByZipCode(new com.caioserralvo.formulario.vo.ZipCode(zipCode)))
                .thenThrow(new NotFoundException("CEP não encontrado"));

        mockMvc.perform(get("/address/" + zipCode))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("CEP não encontrado"));
    }
}
