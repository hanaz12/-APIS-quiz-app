# Quiz and Question API

## Overview
This project provides RESTful APIs to manage questions and quizzes. It includes endpoints for creating, retrieving, updating, and deleting questions, as well as functionalities for creating quizzes and submitting responses.

## Technologies Used
- **Spring Boot**
- **Spring MVC**
- **RESTful APIs**
- **Java 17+**

## Controllers

### QuestionController
Handles CRUD operations related to questions.

#### Endpoints:

| Method   | Endpoint                             | Description                        |
|----------|-------------------------------------|------------------------------------|
| `GET`    | `/questions/all`                    | Retrieves all questions           |
| `GET`    | `/questions/category/{category}`   | Retrieves questions by category   |
| `POST`   | `/questions/add`                    | Adds a new question               |
| `PUT`    | `/questions/update`                 | Updates an existing question      |
| `GET`    | `/questions/{id}`                    | Retrieves a question by ID        |
| `DELETE` | `/questions/delete/{id}`            | Deletes a question by ID          |

### QuizController
Manages quiz creation, retrieval, and submission.

#### Endpoints:

| Method   | Endpoint                                             | Description                                         |
|----------|-----------------------------------------------------|-----------------------------------------------------|
| `POST`   | `/quiz/create?category={category}&numQ={numQ}&title={title}` | Creates a new quiz with specified parameters       |
| `GET`    | `/quiz/get/{id}`                                    | Retrieves questions for a quiz by ID               |
| `POST`   | `/quiz/submit/{id}`                                | Submits quiz responses and calculates results     |

## How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo-link.git

2. Navigate to the project directory:
   ```sh
   cd project-directory
   
3.Build and run the application using Maven
   ```sh
   mvn spring-boot:run

## Usage
   Use a tool like Postman or cURL to test the endpoints
