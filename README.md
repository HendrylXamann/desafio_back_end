# 🧠 API de Torneios de Lógica

Esta é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de torneios de lógica. Ela permite o cadastro de jogadores, criação de torneios, desafios de lógica (como Fibonacci, Palíndromo e Ordenação), registro de pontuação e geração de rankings por torneio.

## 🚀 Funcionalidades

- Cadastro e gerenciamento de **jogadores** (`/players`)
- Criação e gerenciamento de **torneios** e jogadores (`/tournaments`)
- Execução de desafios com avaliação automática (`/challenges/execute`)
- Geração de **ranking por torneio** com base na pontuação acumulada (`/ranking`)

## 🛠️ Tecnologias e versões utilizadas

- **Java**: 21
- **Spring Boot**: 3.4.5
- **JPA / Hibernate** para mapeamento objeto-relacional
- **PostgreSQL**: latest
- **Docker**: 24.0+
- **Flyway**: para versionamento de schema
- **Swagger / Springdoc OpenAPI**: 2.7.0

## ⚙️ Como rodar o projeto

### 1. Clonar o repositório

### 2. Configurar o ambiente com Docker

O projeto já inclui `docker-compose.yml` e `Dockerfile` para facilitar a execução:

```bash
docker-compose up --build
```

Isso irá:

- Subir um container com PostgreSQL
- Construir a aplicação Java
- Executar as migrações Flyway automaticamente
- Disponibilizar a API em `http://localhost:8080`

### 3. Acessar a documentação da API (Swagger)

Após subir o projeto, acesse:

```
http://localhost:8080/swagger-ui.html
```

Ou via Springdoc:

```
http://localhost:8080/swagger-ui/index.html
```

## 🗃️ Estrutura de pacotes

- `controller`: Endpoints REST organizados por entidade
- `service`: Lógica de negócio centralizada
- `model` (ou `entity`): Entidades JPA
- `repository`: Interfaces do Spring Data JPA
- `dto`: Objetos de transferência de dados
- `migration`: Scripts Flyway (em `resources/db/migration`)

## 📌 Observações

- O banco é iniciado com o schema base via Flyway, com ou sem dados iniciais conforme configurado.
- Os desafios lógicos são resolvidos via código Java dentro do service responsável e os resultados são validados automaticamente.

---

