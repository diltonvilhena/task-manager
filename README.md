# Task Manager API 🚀

&#x20; &#x20;

Um gerenciador de tarefas simples e seguro, desenvolvido com **Spring Boot**, **JWT Authentication** e **Swagger** para documentação da API. Este projeto tem como objetivo **praticar autenticação com Spring Security**, boas práticas de **tratamento de erros e logs estruturados**, além de aplicar conceitos sólidos de desenvolvimento backend.

---

## 📌 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x** (Spring Web, Spring Security, Spring Data JPA)
- **JWT (JSON Web Token) para autenticação**
- **Banco de Dados H2** (pode ser alterado para PostgreSQL, MySQL, etc.)
- **Swagger (Springdoc OpenAPI)** para documentação
- **Lombok** para redução de código boilerplate

---

## 🚀 Como Rodar o Projeto

### 🔧 Pré-requisitos

- Ter o **JDK 17+** instalado
- Ter o **Maven** configurado

### 🏗 Passos para executar

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/task-manager-api.git
   ```
2. Entre na pasta do projeto:
   ```sh
   cd task-manager-api
   ```
3. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```
4. A API estará rodando em: `http://localhost:8080`

---

## 🔐 Autenticação JWT

A API utiliza **JSON Web Token (JWT)** para autenticação. Para acessar as rotas protegidas, siga os passos:

1. **Gerar um Token**

    - Faça login enviando uma requisição `POST` para:

   ```sh
   POST http://localhost:8080/auth/login
   ```

    - Corpo da requisição:

   ```json
   {
      "username": "admin",
      "password": "admin"
   }
   ```

    - Resposta esperada:

   ```json
   {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
   }
   ```

2. **Usar o Token para acessar as rotas protegidas**

    - Exemplo de requisição autenticada:

   ```sh
   GET http://localhost:8080/tasks
   ```

    - Cabeçalho:

   ```sh
   Authorization: Bearer SEU_TOKEN_AQUI
   ```

---

## 🔍 Endpoints da API

### ✅ Tarefas (`/tasks`)

| Método   | Endpoint      | Descrição                     |
| -------- | ------------- | ----------------------------- |
| `GET`    | `/tasks`      | Lista todas as tarefas        |
| `GET`    | `/tasks/{id}` | Retorna uma tarefa específica |
| `POST`   | `/tasks`      | Cria uma nova tarefa          |
| `PUT`    | `/tasks/{id}` | Atualiza uma tarefa existente |
| `DELETE` | `/tasks/{id}` | Remove uma tarefa             |

### 🔑 Autenticação (`/auth`)

| Método | Endpoint      | Descrição                                   |
| ------ | ------------- | ------------------------------------------- |
| `POST` | `/auth/login` | Autentica um usuário e retorna um token JWT |

---

## 📖 Documentação da API

A API conta com uma documentação interativa no Swagger:

- **Acesse:** `http://localhost:8080/swagger-ui/index.html#/`

---

## 🛠 Próximos Passos

- Docker (para futura implementação de conteinerização)
- Implementar um frontend para o projeto

---

## 🤝 Contribuição

Sinta-se à vontade para abrir **issues** e **pull requests** para melhorias!

📩 Entre em contato: [diltonvilhena@gmail.com](mailto\:diltonvilhena@gmail.com)

