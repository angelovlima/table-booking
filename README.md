﻿# table-booking-api

## Descrição
Projeto Spring Boot de um gestão de reservas, realizado para o terceiro Tech Challenge da FIAP.

## How to Use

### Executando o Projeto com Docker Compose
Para executar o projeto utilizando Docker Compose, siga os passos abaixo:

1. **Clonar o Repositório**

Primeiro, clone o repositório do projeto:

```bash
git clone https://github.com/angelovlima/table-booking.git
cd table-booking/
```

2. **Subir o Banco de Dados no Docker**

Antes de realizar o build da aplicação, suba o serviço do banco de dados PostgreSQL utilizando Docker. No diretório raiz do projeto, execute o seguinte comando:

```bash
docker-compose up -d postgres
```

3. **Build da Aplicação**

Após o banco de dados estar em execução, construa a aplicação utilizando Maven:

```bash
mvnw.cmd clean install
```

4. **Executar o Docker Compose**

Depois que o build da aplicação for construído, você pode iniciar todos os serviços definidos no arquivo "docker-compose.yaml":

```bash
docker-compose up --build
```

5. **Acessar a Documentação (Swagger)**

Para acessar a documentação Swagger e visualizar os endpoints detalhados, acesse a seguinte URL:

```
http://localhost:8080/swagger-ui.html
```
