# Task Manager
# store
## Overview
Task Manager is a sleek web-based application designed to help you organize and manage tasks with ease. Powered by a Spring Boot backend and a responsive HTML/JavaScript frontend it uses PostgreSQL for persistent storage. Whether you are adding new tasks editing existing ones or toggling completion status Task Manager offers a user-friendly experience complete with light/dark mode support and a RESTful API.

## Features
- Create Tasks: Add tasks with a title optional description and completion status.
- View Tasks: See all tasks in a stylish table with title status and description.
- Edit Tasks: Update task details without generating new IDs.
- Toggle Completion: Mark tasks as complete or undo with one click.
- Delete Tasks: Remove tasks effortlessly.
- Dark Mode: Switch between light and dark themes saved via local storage.
- Responsive UI: Works seamlessly across devices.
- API Access: CRUD operations exposed via REST endpoints with Swagger documentation.

## Tech Stack
- Backend: Spring Boot 3.4.3
- Frontend: HTML JavaScript CSS
- Database: PostgreSQL 42.7.3 (H2 for testing)
- Build Tool: Maven
- Java: 17
- Key Dependencies:
  - spring boot starter web - RESTful services
  - spring boot starter data jpa - Database operations
  - spring boot starter validation - Input validation
  - springdoc openapi starter webmvc ui - Swagger UI (v2.2.0)
  - postgresql - PostgreSQL driver
  - lombok - Boilerplate reduction
  - spring boot devtools - Development convenience

## Prerequisites
- Java 17: Install JDK 17 (java -version to confirm).
- Maven: Required for building (mvn -v to check).
- PostgreSQL: A running instance (e.g. v15.x).
- Git: Optional for cloning.

## Setup Instructions

### 1. Clone the Repository
git clone https://github.com/George-Raymond/store.git
cd store

### 2. Configure PostgreSQL
- Install PostgreSQL if not already set up.
- Create a database:
  CREATE DATABASE taskmanager;
- Add src/main/resources/application.properties with your DB details:
  spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
  spring.datasource.username=george
  spring.datasource.password=1234
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  server.port=8080

### 3. Build and Run
- Build the project:
  mvn clean install
- Launch the app:
  mvn spring-boot:run
- Access it at http://localhost:8080.

### 4. Explore the App
- Browser: Visit http://localhost:8080/index.html to manage tasks.
- Swagger UI: Check http://localhost:8080/swagger-ui/index.html for API docs.

## Usage
- Add a Task: Enter a title optional description and completion status then click Add Task.
- Edit a Task: Click Edit update fields and save.
- Toggle Status: Use Complete or Undo buttons.
- Delete a Task: Hit Delete to remove it.
- Dark Mode: Toggle the switch in the header.

## Project Structure
store/
├── src/
│   ├── main/
│   │   ├── java/io/moshhamedani/store/
│   │   │   ├── controller/TaskController.java    # REST API endpoints
│   │   │   ├── model/Task.java                  # Task entity
│   │   │   ├── repository/TaskRepository.java   # JPA repository
│   │   │   ├── service/TaskService.java         # Business logic
│   │   │   └── StoreApplication.java            # App entry point
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── app.js                       # JS frontend logic
│   │       │   └── styles.css                   # Styling
│   │       ├── templates/
│   │       │   └── index.html                   # Main page
│   │       └── application.properties           # Config file
│   └── test/                                    # Tests
├── pom.xml                                      # Maven config
└── README.md                                    # This file

## API Endpoints

| Method | Endpoint         | Description            | Request Body (JSON) Example                                     |
|--------|------------------|------------------------|--------------------------------------------------               |
| GET    | /api/tasks       | List all tasks         | N/A                                                             |
| GET    | /api/tasks/{id}  | Get task by ID         | N/A                                                             |
| POST   | /api/tasks       | Add a new task         | {"title": "Task", "description": "Details", "completed": false} |
| PUT    | /api/tasks/{id}  | Update a task          | {"title": "Updated", "description": "New", "completed": true}   |
| DELETE | /api/tasks/{id}  | Delete a task          | N/A                                                             |

## Contributing
1. Fork the repo.
2. Create a branch (git checkout -b feature/awesome-idea).
3. Commit changes (git commit -m Add awesome idea).
4. Push (git push origin feature/awesome-idea).
5. Submit a pull request.

## License
MIT License - see LICENSE

## Acknowledgments
- Created by George Raymond with Spring Boot and a love for productivity.
- Thanks to the open-source community for tools like PostgreSQL and Swagger!
