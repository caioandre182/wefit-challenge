package com.caioserralvo.formulario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateUser_whenValidRequest_thenReturns201() throws Exception {
        String jsonPayload = """
        {
          "type": "FISICA",
          "name": "João Silva",
          "email": "joao@email.com",
          "cpf": "41741837839",
          "phone": "1133445566",
          "cellPhone": "11994917188",
          "zipCode": "12345678",
          "street": "Rua das Flores",
          "district": "Centro",
          "number": "123",
          "city": "São Paulo",
          "complement": "Apto 12",
          "stateCode": "SP"
        }
        """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn400_whenCpfIsInvalid() throws Exception {
        String payload = """
    {
      "type": "FISICA",
      "name": "João Silva",
      "email": "joao@email.com",
      "cpf": "11111111111",
      "phone": "1133445566",
      "cellPhone": "11998765432",
      "zipCode": "12345678",
      "street": "Rua das Flores",
      "district": "Centro",
      "number": "123",
      "city": "São Paulo",
      "complement": "Apto 12",
      "stateCode": "SP"
    }
    """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnUser_whenDocumentExists_thenReturns200() throws Exception {
        String jsonPayload = """
        {
          "type": "FISICA",
          "name": "Maria Oliveira",
          "email": "maria@email.com",
          "cpf": "39053344705",
          "phone": "1133445566",
          "cellPhone": "11994917188",
          "zipCode": "03523020",
          "street": "Rua das Palmeiras",
          "district": "Centro",
          "number": "456",
          "city": "São Paulo",
          "complement": "Casa",
          "stateCode": "SP"
        }
        """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated());

        mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                .get("/users/document/39053344705"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .jsonPath("$.name").value("Maria Oliveira"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .jsonPath("$.email").value("maria@email.com"));
    }

    @Test
    public void shouldReturn404_whenDocumentDoesNotExist() throws Exception {
        String nonExistingCpf = "99999999999";

        mockMvc.perform(get("/users/document/" + nonExistingCpf))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("CPF não encotrado na base de dados"));
    }
}
