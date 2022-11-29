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
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_url.png)

#### 1.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_body.png)

A resposta dessa requisição é do tipo status 201 e o seu corpo será:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_response.png)

Se caso o nome não for informado, você irá receber uma resposta com status 400 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post_or_update_team_bad_request.png)


### 1.2 - GET Team By ID:

#### 1.2.1 - URL para buscar um time pelo ID:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_response.png)

Se caso o time não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_teamnotfound.png)


### 1.3 - GET All Teams:

#### 1.3.1 - URL para buscar todos os times:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_allteams_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_allteams_response.png)


### 1.4 - UPDATE o nome do time:

#### 1.4.1 - URL para atualizar o nome de um time:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_url.png)

#### 1.4.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_body.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_response.png)

Se caso o nome não for informado, você irá receber uma resposta com status 400 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post_or_update_team_bad_request.png)


### 1.5 - DELETE a Team By ID:

#### 1.5.1 - URL para apagar um time pelo ID:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_response.png)

Se caso o time não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_team_not_found.png)



## 2 - Métodos referentes a entidade TASK


### 2.1 - Create Task:

#### 2.1.1 - URL para criar uma Task:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_url.png)

#### 2.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_body.png)

A resposta dessa requisição é do tipo status 201 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_response.png)

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_bad_request.png)

Além disso, se caso o Team cujo ID informado não for encontrada, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_team_not_found.png)


### 2.2 - GET Task By ID:

#### 2.2.1 - URL para buscar uma Task pelo ID:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_response.png)

Se caso a Task não for encontrada com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_task_not_found.png)


### 2.3 - GET All Tasks:

#### 2.3.1 - URL para buscar todas as tasks:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_all_tasks_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_all_tasks_response.png)


### 2.4 - UPDATE uma task:

#### 2.4.1 - URL para atualizar uma task:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_url.png)

#### 2.4.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_body.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_response.png)

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_bad_request.png)

Além disso, se caso a Task cujo ID informado não for encontrado, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_task_not_found.png)


### 2.5 - DELETE a Task By ID:

#### 2.5.1 - URL para apagar uma task pelo ID:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_response.png)

Se caso a task não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_team_not_found.png)



## 3 - Métodos referentes a entidade EMPLOYEE


### 3.1 - Create Employee:

#### 3.1.1 - URL para criar um Employee:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_url.png)

#### 3.1.2 - O corpo da requisição precisa ser do tipo JSON, abaixo está um exemplo do mesmo:
![BODY da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_body.png)

A resposta dessa requisição é do tipo status 201 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_response.png)

Para cada dado não informado, você terá uma mensagem na resposta informando essa falta, além diso essa resposta é do tipo 400, abaixo está um exemplo da mesma:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_bad_request.png)

Além disso, se caso o Team cujo ID informado não for encontrado, você terá uma resposta do tipo 404 com o seguinte corpo:
![RESPONSE Do time não encontrado da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_team_not_found.png)


### 3.2 - GET Employee By ID:

#### 3.2.1 - URL para buscar um Employee pelo ID:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_response.png)

Se caso o Employee não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_employee_not_found.png)


### 3.3 - GET All Employees:

#### 3.3.1 - URL para buscar todas os Employees:
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_all_employees_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_all_employees_response.png)


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
![URL da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_url.png)

A resposta dessa requisição é do tipo status 200 e o seu corpo terá:
![RESPONSE da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_response.png)

Se caso o Employee não for encontrado com o ID informado será enviada uma resposta com status 404 e em seu corpo terá:
![RESPONSE DE BAD REQUEST da requisição POST](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_not_found.png)


## Aims

The main aims of the application are:
 - Adquirir experiência com criação de API;
 - Aprender como utilizar o Java e o Ecossistema Spring para construir uma API;
 - Aprender sobre testes unitários e de integração dentro do Java atrelado com Spring;
 - Aprender sobre documentação de APIs com Swagger


## Links
  - Repository: https://github.com/felipemarques001/task-management-api
    - In case of sensitive bugs like security vulnerabilities, please contact
      felipemarquesgg@outlook.com directly instead of using issue tracker. We value your effort
      to improve the security and privacy of this project!

  ## Versioning
  1.0.0


  ## Authors

  * **Antônio Felipe Rocha Ferreira Marques** 

  Please follow github and join us!
  Thanks to visiting me and good coding!
