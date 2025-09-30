# HMCTS Task Management System

A Spring Boot web application for HMCTS that enables caseworkers to efficiently track and manage tasks using Docker.

## Features

- **Task Management**: Create, read, update, and delete tasks
- **Status Tracking**: Track task status (Pending, In Progress, Completed, Cancelled)
- **Web Interface**: User-friendly Thymeleaf-based web interface
- **REST API**: Complete REST API for task operations
- **Status Filtering**: Filter tasks by status
- **Responsive Design**: Bootstrap-based responsive UI
- **Dockerized**: Fully containerized with PostgreSQL database

## Technology Stack

- **Backend**: Spring Boot 3.x
- **Frontend**: Thymeleaf templates with Bootstrap 5
- **Database**: PostgreSQL 15
- **Containerization**: Docker & Docker Compose
- **Build Tool**: Maven
- **Java Version**: 17+

## Getting Started

- Docker Desktop
- Docker Compose

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd hmcts
   ```

2. Build and start the application:
   ```bash
   docker-compose up --build -d
   ```

3. Access the application:
   - **Web Interface**: http://localhost:8080/tasks
   - **API Documentation**: http://localhost:8080/swagger-ui.html

### Stopping the Application

```bash
docker-compose down
```

## Docker Services

- **PostgreSQL Database**: 
  - Port: 5432
  - Database: `hmcts_db`
  - User: `my_user`
  - Password: `my_password`
- **Spring Boot Application**: Port 8080

## API Endpoints

### Web Interface
- `GET /tasks` - View all tasks
- `GET /tasks/new` - Create new task form
- `POST /tasks` - Create new task
- `GET /tasks/{id}/edit` - Edit task form
- `POST /tasks/{id}` - Update task
- `POST /tasks/{id}/delete` - Delete task
- `POST /tasks/{id}/status` - Update task status
- `GET /tasks/status/{status}` - Filter tasks by status

### REST API
- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create new task
- `PUT /api/tasks/{id}` - Update task
- `PATCH /api/tasks/{id}/status` - Update task status
- `DELETE /api/tasks/{id}` - Delete task

## Task Model

Each task contains:
- **ID**: Unique identifier
- **Title**: Task title (required)
- **Description**: Task description (optional)
- **Status**: Task status (PENDING, IN_PROGRESS, COMPLETED, CANCELLED)
- **Due Date**: Task due date and time (required)
- **Created At**: Creation timestamp
- **Updated At**: Last update timestamp
