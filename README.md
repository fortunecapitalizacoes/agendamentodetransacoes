# Sistema de Agendamento de Transferências Financeiras

Este é um sistema de agendamento de transferências financeiras desenvolvido com **Spring Boot** no back-end. O sistema permite que os usuários agendem transferências financeiras entre contas, calculem automaticamente taxas de transferência com base na data e visualizem o extrato de agendamentos realizados.

## Tecnologias Utilizadas

- **URL-FRONTEND**:

```bash
https://github.com/fortunecapitalizacoes/agendamentodetransacoes-frontend
```
	
- **Back-end**:
  - **Spring Boot 3.4.3** (Java 11)
  - **Spring Data JPA** (para persistência em banco de dados H2)
  - **Swagger/OpenAPI 3** (para documentação da API)
  - **Lombok** (para simplificar o código)
  - **JUnit 5** (para testes unitários)
  
- **Banco de Dados**:
  - **H2 Database** (banco de dados em memória)

## Requisitos

Antes de rodar o projeto, é necessário ter o seguinte instalado:

- **Java 17** ou superior
- **Gradle ** (para build do projeto)

## Instruções para Execução

### 1. Clonando o Repositório

Clone o repositório para a sua máquina local:

```bash
git clone https://github.com/seuusuario/agendamentodetransacoes.git
cd agendamentodetransacoes
```

### 2. Executando o Back-end (Spring Boot)

Para rodar a aplicação Spring Boot, basta executar o seguinte comando:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada no endereço http://localhost:8080.

### 3. Acessando o Swagger

A documentação do Swagger estará disponível no seguinte endereço: http://localhost:8080/doc.html.


## Pontos de Melhoria

### 1. Adoção de Programação Reativa

Dado o alto volume de requisições simultâneas que uma API de agendamento de transações pode processar, é crucial garantir um desempenho escalável e responsivo. A utilização do Spring WebFlux oferece uma abordagem reativa que possibilita o tratamento assíncrono e não bloqueante de requisições. Esse modelo de programação permite que a API lide de forma eficiente com múltiplas requisições simultâneas, utilizando recursos de forma otimizada e melhorando a escalabilidade da aplicação. Além disso, o Spring WebFlux proporciona uma resposta mais rápida ao consumidor, uma vez que não bloqueia threads durante o processamento de requisições, o que resulta em menor latência e maior capacidade de gerenciamento de conexões em sistemas de alto tráfego.


### 2. Validação de Usuários

Implementar uma funcionalidade que permita a obtenção do usuário com base no access token fornecido na requisição. Este token será validado para autenticar o usuário, e a partir da informação extraída, será possível aplicar um filtro nas consultas de agendamentos. O filtro garantirá que apenas os agendamentos associados ao usuário autenticado sejam retornados, proporcionando segurança e personalização ao processo de recuperação de dados. Esse mecanismo de autenticação e autorização deve ser projetado de maneira eficiente, utilizando as melhores práticas de segurança para garantir a integridade das informações do usuário.