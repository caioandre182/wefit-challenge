# WeFit Challenge - Formulário


## Descrição
Este projeto consiste em um endpoint que recebe os dados de um 
formulário contendo informações pessoais, de contato e endereço. 
O desafio propõe criar um endpoint que torne o formulário da imagem abaixo, funcionar corretamente.


![Formulário](src/docs/form_usuario.png)


## Tecnologias utilizadas

- Java 17
- Spring Boot
- Jakarta Validation (Bean Validation)
- Lombok
- Swagger (Springdoc OpenAPI)
- Arquitetura em camadas (com VOs, DTOs e tratamento de exceções)


## Como rodar o projeto localmente

### Como clonar e executar
```bash
git clone https://github.com/caioandre182/wefit-challenge.git
cd wefit-challenge
./mvnw spring-boot:run
```
## Para testar

http://localhost:8080/swagger-ui.html


## Exemplo de payload

```json
{
  "tipo": "FISICA",
  "nome": "João da Silva",
  "email": "joao@email.com",
  "cpf": "390.533.447-05",
  "cnpj": "",
  "telefone": "(11) 3333-4444",
  "celular": "(11) 98888-9999",
  "cep": "04547-000",
  "logradouro": "Av. Paulista",
  "bairro": "Bela Vista",
  "numero": "123",
  "cidade": "São Paulo",
  "complemento": "Apto 45",
  "uf": "SP"
}