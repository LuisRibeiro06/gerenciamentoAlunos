# Gerenciar Alunos

<img width="1101" height="320" alt="image" src="https://github.com/user-attachments/assets/37ef1e72-d407-4fc3-bd44-8b173d680242" />


Aplicação REST em Spring Boot para gestão de alunos e suas matrículas.

Permite:
- Cadastrar aluno com uma ou mais matrículas
- Listar alunos
- Listar matrículas de um aluno
- Atualizar dados e matrículas de um aluno
- Remover aluno

## Stack
- Java 21
- Spring Boot 3 (Web, Data JPA, Validation)
- Banco em memória H2 (runtime)
- Maven
- Lombok

## Requisitos
- Java 21 instalado
- Maven (ou usar o wrapper `mvnw`/`mvnw.cmd` incluído)

## Como executar
No Windows (PowerShell):

- Usando o Maven instalado:
  ```powershell
  mvn clean spring-boot:run
  ```

- Usando o wrapper do Maven:
  ```powershell
  .\mvnw.cmd clean spring-boot:run
  ```

A aplicação iniciará por padrão em: http://localhost:8080

Banco de dados: H2 em memória (sem configuração adicional). A cada reinício os dados são recriados.

## Estrutura principal
- `com.example.gerenciarAlunos.controller.AlunoController` — Endpoints REST
- `com.example.gerenciarAlunos.service.AlunoService` — Regras de negócio
- `com.example.gerenciarAlunos.domain.*` — Entidades JPA (Aluno, Matricula)
- `com.example.gerenciarAlunos.dto.*` — DTOs de entrada/saída
- `com.example.gerenciarAlunos.mapper.AlunoMapper` — Conversão entre entidades e DTOs

## Endpoints
Base: `/alunos`

1) Criar aluno
- Método: POST
- URL: `/alunos`
- Corpo (application/json):
  ```json
  {
    "nome": "Aluno",
    "telefone": "123456789",
    "dataNascimento": "1990-01-01",
    "matriculas": [
      { "codigoMatricula": "1", "curso": "Matemática", "dataMatricula": "2022-01-01" },
      { "codigoMatricula": "2", "curso": "Português", "dataMatricula": "2022-02-01" }
    ]
  }
  ```
- Respostas:
  - 201 Created com `AlunoResponse`
  - 400 Bad Request em caso de validação inválida

Exemplo cURL:
```bash
curl -X POST http://localhost:8080/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Aluno",
    "telefone": "123456789",
    "dataNascimento": "1990-01-01",
    "matriculas": [
      { "codigoMatricula": "1", "curso": "Matemática", "dataMatricula": "2022-01-01" },
      { "codigoMatricula": "2", "curso": "Português", "dataMatricula": "2022-02-01" }
    ]
  }'
```

2) Listar alunos
- Método: GET
- URL: `/alunos`
- Resposta: 200 OK com lista de `AlunoResponse`

Exemplo cURL:
```bash
curl http://localhost:8080/alunos
```

3) Listar matrículas de um aluno
- Método: GET
- URL: `/alunos/{id}/matriculas`
- Resposta: 200 OK com lista de `MatriculaDTO`
- Erros: 404 se aluno não encontrado

Exemplo cURL:
```bash
curl http://localhost:8080/alunos/1/matriculas
```

4) Atualizar aluno
- Método: PUT
- URL: `/alunos/{id}`
- Corpo: mesmo formato do POST
- Resposta: 200 OK com `AlunoResponse`
- Erros: 404 se aluno não encontrado

Exemplo cURL:
```bash
curl -X PUT http://localhost:8080/alunos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Aluno Atualizado",
    "telefone": "987654321",
    "dataNascimento": "1990-01-01",
    "matriculas": [
      { "codigoMatricula": "3", "curso": "História", "dataMatricula": "2023-01-01" }
    ]
  }'
```

5) Remover aluno
- Método: DELETE
- URL: `/alunos/{id}`
- Resposta: 204 No Content
- Erros: 404 se aluno não encontrado

Exemplo cURL:
```bash
curl -X DELETE http://localhost:8080/alunos/1
```

Observações:
- `codigoMatricula` é tratado como texto (string) no domínio/DTO; use aspas no JSON.
- Validações: nome, telefone, dataNascimento e lista de matrículas são obrigatórios.

## Arquivo de requisições (IntelliJ HTTP Client)
O projeto inclui `requests.http` com exemplos prontos:
- Localização: `gerenciarAlunos/requests.http`
- Pode ser executado diretamente no IntelliJ IDEA (clique em "Run" acima de cada requisição)

## Executando testes
```powershell
mvn test
```
ou
```powershell
.\mvnw.cmd test
```

## Erros e exceções
- Quando um ID não existe, o serviço lança `EntityNotFoundException` com mensagem "Aluno não encontrado" (retorno HTTP padrão pode variar sem um handler global configurado).

## Licença
Uso educacional/demonstrativo.
