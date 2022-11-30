## Task Management API
This is an API made by me that manages tasks and people management in teams.


## Technology

Here are the technologies used in this project.

* Java
* Spring 
* PostgreSQL
* H2

## Services Used

* Github

## Java frameworks and libraries used in this project:

* Spring Boot
* Spring Data JPA
* Validation
* Lombok
* Swagger
* JUnit
* Mockito
* MockMvc


# Available methods in the API:
**OBS: As this API is not in production, the domain of the URLS that are being shown below is as "domainexample", if this API is for production level, I will change these images to put the URLS with the production domain .**
## 1 - TEAM entity methods:

### 1.1 - Create (POST) a Team:

#### 1.1.1 - Request URL:
![POST - Request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_url.png)

#### 1.1.2 - Request body: The body request is of JSON type, below there is an example it:
![POST - Request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_body.png)

#### 1.1.3 - Response: The response has the 201 status, below there is an example it:
![POST - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post/post_team_response.png)

If the name is not informed, you will receive the following response with the 400 status: <br />
![POST - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post_or_update_team_bad_request.png)


### 1.2 - GET Team By ID:

#### 1.2.1 - Request URL:
![GET team by id - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_url.png)

**The number inside URL refers to the Team ID that you want to get.**

#### 1.2.2 - Response: The response has the 200 status, below there is an example it:
![GET team by id - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_response.png)

If the team was not found with the informed ID, you will receive the following response with 404 status: <br /> 
![GET team by id - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_teambyid_teamnotfound.png)


### 1.3 - GET All Teams:

#### 1.3.1 - Request URL:
![GET all teams - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_allteams_url.png)

#### 1.3.2 - Response: The response has the 200 status, below there is an example it:
![GET all teams - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/get/get_allteams_response.png)


### 1.4 - UPDATE team name:

#### 1.4.1 - Request URL:
![UPDATE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_url.png)

**The number inside URL refers to the Team ID that you want to update.**

#### 1.4.2 - Request body: The body request is of JSON type, below there is an example it:
![UPDATE - request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_body.png)

#### 1.4.3 - Response: The response has the 200 status, below there is an example it:
![UPDATE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_response.png)

If the name is not informed, you will receive the following response with the 400 status: <br />
![UPDATE - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/post_or_update_team_bad_request.png)

If the team was not found with the informed ID, you will receive the following response with 404 status: <br />
![UPDATE - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/update/update_team_not_found.png)

### 1.5 - DELETE Team By ID:

#### 1.5.1 - Request URL:
![DELETE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_url.png)

**The number inside URL refers to the Team ID that you want to delete.**

#### 1.5.2 - Response: The response has the 200 status, below there is an example it:
![DELETE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_response.png)

If the team was not found with the informed ID, you will receive the following response with 404 status: <br />
![DELETE - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Team/delete/delete_team_team_not_found.png)



## 2 - Task entity methods


### 2.1 - Create (POST) Task:

#### 2.1.1 - Request URL:
![POST - request url](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_url.png)

#### 2.1.2 - Request body: The body request is of JSON type, below there is an example it:
![POST - request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_body.png)

#### 2.1.3 - Response: The response has the 201 status, below there is an example it:
![POST - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_response.png)

For each data not informed, you will have a message in the response informing this lack, this response is of type 400, below there is an example of the same: <br />
![POST - bad request response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_bad_request.png)

If the Team whose ID provided was not found, you will receive the following response with the 400 status: <br />
![POST - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/create/create_task_team_not_found.png)


### 2.2 - GET Task By ID:

#### 2.2.1 - Request URL:
![GET by id - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_url.png)

**The number inside URL refers to the Task ID that you want to get.**

#### 2.2.2 - Response: The response has the 200 status, below there is an example it:
![GET by id - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_response.png)

If the task was not found with the provided ID, you will receive the following response with 404 status: <br />
![GET by id - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_task_by_id_task_not_found.png)


### 2.3 - GET All Tasks:

#### 2.3.1 - Request URL:
![GET all - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_all_tasks_url.png)

#### 2.3.2 - Response: The response has the 200 status, below there is an example it:
![GET all - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/get/get_all_tasks_response.png)


### 2.4 - UPDATE Task:

#### 2.4.1 - Request URL:
![UPDATE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_url.png)

**The number inside URL refers to the Task ID that you want to update.**

#### 2.4.2 - Request body: The body request is of JSON type, below there is an example it:
![UPDATE - request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_body.png)

#### 2.4.3 - Response: The response has the 200 status, below there is an example it:
![UPDATE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_response.png)

For each data not informed, you will have a message in the response informing this lack, this response is of type 400, below there is an example of the same: <br />
![UPDATE - bad request response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_bad_request.png)

If the task was not found with the provided ID, you will receive the following response with 404 status: <br />
![UPDATE - task not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_task_not_found.png)

If the team was not found with the provided ID, you will receive the following response with 404 status: <br />
![UPDATE - team not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/update/update_task_team_not_found.png)

### 2.5 - DELETE Task By ID:

#### 2.5.1 - Request URL:
![DELETE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_url.png)

**The number inside URL refers to the Task ID that you want to delete.**

#### 2.5.2 - Response: The response has the 200 status, below there is an example it:
![DELETE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_response.png)

If the task was not found with the provided ID, you will receive the following response with 404 status: <br />
![DELETE - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Task/delete/delete_task_team_not_found.png)



## 3 - Employee entity methods


### 3.1 - Create (POST) Employee:

#### 3.1.1 - Request URL:
![POST - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_url.png)

#### 3.1.2 - Request body: The body request is of JSON type, below there is an example it:
![POST - request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_body.png)

#### 3.1.3 - Response: The response has the 201 status, below there is an example it:
![POST - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_response.png)

For each data not provided, you will have a message in the response informing this lack, this response is of type 400, below there is an example of the same: <br />
![POST - bad request response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_bad_request.png)

If the Team whose ID provided was not found, you will receive the following response with the 400 status: <br />
![POST - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/create/create_employee_team_not_found.png)


### 3.2 - GET Employee By ID:

#### 3.2.1 - Request URL:
![GET by id - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_url.png)

**The number inside URL refers to the Employee ID that you want to get.**

#### 3.2.2 - Response: The response has the 200 status, below there is an example it:
![GET by id - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_response.png)

If the employee was not found with the informed ID, you will receive the following response with 404 status: <br />
![GET by id - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_employee_by_id_employee_not_found.png)


### 3.3 - GET All Employees:

#### 3.3.1 - Request URL:
![GET all - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_all_employees_url.png)

#### 3.3.2 - Response: The response has the 200 status, below there is an example it:
![GET all - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/get/get_all_employees_response.png)


### 3.4 - UPDATE Employee:

#### 3.4.1 - Request URL:
![UPDATE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_url.png)

**The number inside URL refers to the Employee ID that you want to update.**

#### 3.4.2 - Request body: The body request is of JSON type, below there is an example it:
![UPDATE - request body](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_body.png)

#### 3.4.2 - Response: The response has the 200 status, below there is an example it:
![UPDATE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_response.png)

For each data not provided, you will have a message in the response informing this lack, this response is of type 400, below there is an example of the same: <br />
![UPDATE - bad request response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_bad_request_response.png)

If the employee was not found with the informed ID, you will receive the following response with 404 status: <br />
![UPDATE - employee not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_not_found_response.png)

If the team was not found with the provided ID, you will receive the following response with 404 status: <br />
![UPDATE - team not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/update/update_employee_team_not_found_response.png)


### 3.5 - DELETE an Employee By ID:

#### 3.5.1 - Request URL:
![DELETE - request URL](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_url.png)

**The number inside URL refers to the Employee ID that you want to delete.**

#### 3.4.2 - Response: The response has the 200 status, below there is an example it:
![DELETE - response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_response.png)

If the employee was not found with the informed ID, you will receive the following response with 404 status: <br />
![DELETE - not found response](https://github.com/felipemarques001/task-management-api/blob/main/ReadmeImages/Employee/delete/delete_employee_not_found.png)


## Aims

The main aims of the application are:
 - Get experience with API creation;
 - Learn about how tu use Java and Spring ecosystem to build an API;
 - Learn about unit test and integration test inside Java and Spring;
 - Learn about how to build API documentation with Swagger;


## Links
  - Repository: https://github.com/felipemarques001/task-management-api
    - In case of sensitive bugs like security vulnerabilities, please contact
      felipemarquesgg@outlook.com directly instead of using issue tracker. We value your effort
      to improve the security and privacy of this project!

  ## Versioning
  1.0.0


  ## Authors

  * **Antônio Felipe Rocha Ferreira Marques** 

  Please follow GitHub and join us!
  Thanks to visiting me and good coding!
