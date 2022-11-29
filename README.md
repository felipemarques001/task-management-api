## Task Management API
Essa é uma API feita por mim no qual oferece um gerenciamento de tarefas e pessoas dentro de times.


## Technology 

Here are the technologies used in this project.

* Java
* Spring 
* PostgreSQL
* H2

## Services Used

* Github

## Frameworks e bibliotecas do Java usadas nesse projeto

* Spring Boot
* Spring Data JPA
* Validation
* Lombok
* Swagger
* JUnit
* Mockito
* MockMvc


# Métodos disponívies da api

## 1 - Métodos referentes a entidade TEAM

### 1.1 - POST Team:

#### 1.1.1 - URL para criar um time:
![URL da requisição POST]()

#### 1.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 201 e o seu corpo será:
![RESPONSE da requisição POST]()

Se caso o nome não for informado, você irá receber uma resposta com status 400 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


### 1.2 - GET Team By ID:

#### 1.2.1 - URL para buscar um time pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso o time não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


### 1.3 - GET All Teams:

#### 1.3.1 - URL para buscar todos os times:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()


### 1.4 - UPDATE o nome do time:

#### 1.4.1 - URL para atualizar o nome de um time:
![URL da requisição POST]()

#### 1.4.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso o nome não for informado, você irá receber uma resposta com status 400 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


### 1.5 - DELETE a Team By ID:

#### 1.5.1 - URL para apagar um time pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso o time não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()



## 2 - Métodos referentes a entidade TASK


### 2.1 - Create Task:

#### 2.1.1 - URL para criar uma Task:
![URL da requisição POST]()

#### 2.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 201 e o seu corpo terá:
![RESPONSE da requisição POST]()

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST]()

Além disso, se caso o Team cujo ID informado não for encontrada, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST]()


### 2.2 - GET Task By ID:

#### 2.2.1 - URL para buscar uma Task pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso a Task não for encontrada com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


### 2.3 - GET All Tasks:

#### 2.3.1 - URL para buscar todas as tasks:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()


### 2.4 - UPDATE uma task:

#### 2.4.1 - URL para atualizar uma task:
![URL da requisição POST]()

#### 2.4.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST]()

Além disso, se caso a Task cujo ID informado não for encontrado, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST]()


### 2.5 - DELETE a Task By ID:

#### 2.5.1 - URL para apagar uma task pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso a task não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()



## 3 - Métodos referentes a entidade EMPLOYEE


### 3.1 - Create Employee:

#### 3.1.1 - URL para criar um Employee:
![URL da requisição POST]()

#### 3.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 201 e o seu corpo terá:
![RESPONSE da requisição POST]()

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST]()

Além disso, se caso o Team cujo ID informado não for encontrado, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST]()


### 3.2 - GET Employee By ID:

#### 3.2.1 - URL para buscar um Employee pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso o Employee não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


### 3.3 - GET All Employees:

#### 3.3.1 - URL para buscar todas os Employees:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()


### 3.4 - Atualizar um Employee:

#### 3.4.1 - URL para atualizar um Employee:
![URL da requisição POST]()

#### 3.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST]()

Além disso, se caso o Employee cujo ID informado não for encontrado, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST]()


### 3.5 - DELETE a Employee By ID:

#### 3.5.1 - URL para apagar um Employee pelo ID:
![URL da requisição POST]()

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST]()

Se caso o Employee não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST]()


## Aims

The main aims of the application are:
 - Adquirir experiência com criação de API;
 - Aprender como utilizar o Java e o Ecossistema Spring para construir uma API;
 - Aprender sobre testes unitários e de integração dentro do Java atrelado com Spring;
 - Aprender sobre documentação de APIs com Swagger


## Links
  - Repository: https://github.com/Lucasdfg07/Blog_Lucas_Fernandes
    - In case of sensitive bugs like security vulnerabilities, please contact
      felipemarquesgg@outlook.com directly instead of using issue tracker. We value your effort
      to improve the security and privacy of this project!

  ## Versioning

  1.0.0


  ## Authors

  * **Antônio Felipe Rocha Ferreira Marques** 

  Please follow github and join us!
  Thanks to visiting me and good coding!
