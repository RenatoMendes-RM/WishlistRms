# WishlistRms

Desafio Técnico desenvolvido por Renato Mendes.

## Descrição

O **WishlistRms** é uma aplicação Java desenvolvida com Spring Boot, que tem como objetivo gerenciar listas de desejos (wishlists). O projeto utiliza MongoDB como banco de dados e segue boas práticas de desenvolvimento, incluindo testes automatizados com JUnit e Cucumber.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **MongoDB**
- **MapStruct**
- **JUnit 5**
- **Cucumber**
- **Jacoco** (cobertura de testes)

## Pré-requisitos

- Java 21 instalado
- Maven 3.x instalado
- MongoDB em execução

## Como executar o projeto

. Clone o repositório:
```
   git clone https://github.com/RenatoMendes-RM/WishlistRms.git
   cd WishlistRms
```
Para executar na IDE IntelliJ é necessário;

- Criar variável de ambiente no painel de configuração da IDE
```
   MONGODB_URI=mongodb://localhost:27018/wishlists
```
- Subir mongoDB no Docker para executar junto com a IDE IntelliJ:
```  
   docker run -d --name mongo-wishlist-local -p 27018:27017 mongo:latest
```

Para configurar esta variável de ambiente no console:
- Windows (prompt de comando CMD)
```
  set SPRING_DATA_MONGODB_URI=mongodb://localhost:27018/wishlists
```

- Git Bash (e em terminais Unix/Linux em geral):
```
  export SPRING_DATA_MONGODB_URI="mongodb://localhost:27018/wishlists"
```

• Para executar aplicação com Docker:
  Gere o jar da sua aplicação
```
   mvn clean package
```

• Suba tudo com Docker Compose
```
   docker-compose up --build
```

• Para encerrar a execução no container docker:
```
   docker-compose down
```

