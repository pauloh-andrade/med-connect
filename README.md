# MedConnect

Este é o README para a aplicação **[medConnect]**.

## Integrantes do Grupo

- **[Paulo Henrique de Andrade Junior]:** Desenvolvedor Java Backend
- **[Filipe de Oliveira Mendes] rm98959  :** Desenvolvedor Front-end.
- **[Fernando Paparelli]:** Breve apresentação ou responsabilidades no projeto.
- **[Vinicius]:** Breve apresentação ou responsabilidades no projeto.

## Como Rodar a Aplicação

Para rodar a aplicação, siga estas etapas:

1. **Pré-requisitos:** 
  - Java Development Kit (JDK) instalado na sua máquina. Você pode baixar e instalar o JDK a partir do site oficial da Oracle ou usar uma distribuição OpenJDK.
  - Maven instalado na sua máquina.

  
2. **Configuração:**
   - **Clone o Repositório:**
  
      ```bash
      git clone https://github.com/pauloh-andrade/med-connect
      ```
  
   - **Navegue até o Diretório do Projeto:**
  
      ```bash
      cd medconnect
      ```

3. **Execução:** 

    - **Compile o Projeto:**

      ```bash
      mvn compile
      ```

    - **Execute a Aplicação:**

      ```bash
      mvn exec:java -Dexec.mainClass="nome.do.pacote.da.classe.Principal"
      ```

## Diagramas

Aqui está a imagem dos diagramas utilizados no projeto:

[Arquitetura](https://github.com/pauloh-andrade/med-connect/blob/main/documents/arquitetura.drawio.png)
[Diagrama Entidade Relacionamento](https://github.com/pauloh-andrade/med-connect/blob/main/documents/DER.pdf)

## Cronograma

[Azure Board](https://github.com/pauloh-andrade/med-connect/blob/main/documents/cronograma_sprint_1.png)

## Vídeo de Apresentação

Assista ao vídeo de apresentação da nossa proposta tecnológica, público-alvo e problemas que a aplicação se propõe a solucionar [aqui](link-para-o-vídeo).

## Documentação da API

A seguir está a listagem de todos os endpoints da API:

**[Postman Collection](https://github.com/pauloh-andrade/med-connect/blob/main/documents/postaman/medConnect.postman_collection.json)**

- **GET /clientes:** Retorna uma lista de todos os clientes.
- **GET /clientes/{id}:** Retorna os detalhes de um cliente específico pelo ID.
- **POST /clientes:** Cria um novo cliente.
    - **Body:**
    ```json 
    {
      "nome": "Maria Silva",
      "cpf": 12345678900,
      "rg": "1234567",
      "login": "maria123",
      "senha": "senha456",
      "dataNascimento": "1985-03-20",
      "dataCadastro": "2024-04-15T10:30:00",
      "nomeUsuario": "maria_silva",
      "emails": [
        {
          "email": "maria@example.com",
          "dataCadastro": "2024-04-15T10:30:00",
          "nomeUsuario": "maria_silva"
        }
      ],
      "contatos": [
        {
          "ddi": 55,
          "ddd": 11,
          "telefone": 987654321,
          "dataCadastro": "2024-04-15T10:30:00",
          "nomeUsuario": "maria_silva"
        }
      ]
  }
    ```


---
