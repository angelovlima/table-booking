# table-booking-api

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

6. **Deploy AWS**

conecte-se em sua instância AWS

```bash
ssh ec2-user@ec2-3-15-13-69.us-east-2.compute.amazonaws.com
```

Atualize a máquina e instale o Java

```bash
sudo yum update

sudo yum install java-17-amazon-corretto-headless

java --version
```

No terminal do projeto, copie o jar para sua instância

```bash
scp .\target\table-booking-api-1.0.0-SNAPSHOT.jar ec2-user@ec2-3-15-13-69.us-east-2.compute.amazonaws.com:/home/ec2-user
```

Acesse a instância pelo terminal e rode o jar

```bash
ssh ec2-user@ec2-3-15-13-69.us-east-2.compute.amazonaws.com

java -jar table-booking-api-1.0.0-SNAPSHOT.jar
```