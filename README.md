# DESAFIO BUILDERS MICROSERVICE

Projeto foi feito usando arquitetura básica em micro serviço

## Requisitos do desafio
Desenvolva uma REST API que:

- Permita criação de novos clientes;
- Permita a atualização de clientes existentes;
- Permita que seja possível listar os clientes de forma paginada;
- Permita que buscas por atributos cadastrais do cliente;
- É necessário também que cada elemento retornado pela api de clientes informe a idade;
- Documente sua API e também disponibilize um arquivo Postman para fácil utilização da API.

Nas próximas seções serão apresentados os requisitos e o procedimento para realizar o setup da aplicação.

## Requisitos para subir o sistema

Para montar o ambiente do projeto é necessário:

* Java 8
* Maven
* Git

## Configuração do Backend

Este projeto foi desenvolvido utilizando a arquitetura de microsserviços e conta com vários módulos para seu completo funcionamento.

É necessário subir todos os módulos para a aplicação funcionar.

### Importando Dependências

Para importar as dependencias basta ir no pacote principal e rodar o comando abaixo:

    mvn package

### Discovery

Deve ser o primeiro módulo a ser iniciado. Este módulo que registra os microservices ativos, para acessar a lista de serviços registrado acesse 

    http://localhost:8081

Para iniciar o discovery fora da IDE, basta executar o comando abaixo dentro da pasta discovery:
 
    mvn spring-boot:run


### Gateway

Logo após o gateway. Este módulo faz a ponte entre o frontend e os microservices no backend.

Para iniciar o gateway fora da IDE, basta executar o comando abaixo dentro da pasta gateway:


### Client-Service

Após iniciar o gateway e discovery já é possível iniciar o modulo principal o Client-Service. Este módulo contém o crud de clientes.

Para iniciar o serviço client-service fora da IDE, basta executar o comando abaixo dentro da pasta client-service:
 
    mvn spring-boot:run

### Gateway

O ultimo módulo a ser iniciado é o gateway. Este módulo faz a ponte entre o frontend e os microservices no backend.

Para iniciar o gateway fora da IDE, basta executar o comando abaixo dentro da pasta gateway:
 
    mvn spring-boot:run

## Configurações dos projetos nas IDEs

Este projeto é um projeto Maven. Com isso, o processo de configuração dele é o padrão de qualquer projeto maven.

Basta importar um novo projeto maven apontando para o pom parent localizado na raíz do projeto.

Os demais detalhes de configurações fica a critério de cada IDE utilizada.

## Swagger

O projeto possui uma documentação para o serviço de comércio para acessar basta acessar a url abaixo

    http://localhost:8082/swagger-ui.html#/