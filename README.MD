# InveMedi

InveMedi é um sistema de gerenciamento de estoque hospitalar desenvolvido com Spring Boot e Java. Ele permite que os usuários gerenciem itens de estoque hospitalar, incluindo a criação, atualização, exclusão e visualização de itens.

## Funcionalidades

- Gerenciamento de usuários
- Gerenciamento de itens de estoque hospitalar
- Autenticação e autorização com JWT
- Manipulação de exceções globais
- Integração com banco de dados MySQL

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.3
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- JWT (JSON Web Token)
- Maven

## Configuração do Ambiente

1. Clone o repositório:
   ```sh
   git clone https://github.com/EdiellyF/inveMedi.git


## Configure o banco de dados MySQL no arquivo application.properties

```spring.datasource.password=123mudarspring.datasource.username=rootspring.datasource.url=jdbc:mysql://localhost:3306/invemedi?useSSL=false&serverTimezone=UTC```

##Execute o projeto usando maven
``./mvnw spring-boot:run``


### Usuários

- `GET /user/{id}`: Busca um usuário pelo ID
- `POST /user`: Cria um novo usuário
- `PUT /user/{id}`: Atualiza um usuário existente
- `DELETE /user/{id}`: Deleta um usuário

### Itens de Estoque

- `GET /itens/{id}`: Busca um item de estoque pelo ID
- `GET /itens/user/{userId}`: Busca todos os itens de estoque de um usuário
- `POST /itens`: Cria um novo item de estoque
- `PUT /itens/{id}`: Atualiza um item de estoque existente
- `DELETE /itens/{id}`: Deleta um item de estoque