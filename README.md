
# Task Manager API

Task Manager API is a project built with Spring Boot that allows managing user tasks. It enables creating, updating, deleting, and viewing tasks.

## Features

- Creating new tasks for a user.
- Retrieving a list of all tasks.
- Filtering tasks by status (`NEW`, `IN_PROGRESS`, `COMPLETED`).
- Retrieving task details by ID.
- Updating an existing task.
- Deleting a task.

## Technologies

- **Language**: Java 21
- **Framework**: Spring Boot 3
- **Database**: H2 (In-Memory)
- **Build Tool**: Gradle

## Requirements

- JDK 21 or later
- Gradle 7.6 or later

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/USERNAME/task-manager-api.git
   cd task-manager-api
   ```

2. **Build the project:**
   ```bash
   ./gradlew build
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

4. **Default API address:**
   ```
   http://localhost:8080/api/tasks
   ```

## API Endpoints

### **Create a New Task**
- **POST** `/api/tasks`
- **Body (JSON):**
  ```json
  {
    "userId": 1,
    "title": "New Task",
    "description": "Task Description"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "title": "New Task",
    "description": "Task Description",
    "status": "NEW",
    "createdAt": "2023-01-01T12:00:00",
    "updatedAt": "2023-01-01T12:00:00"
  }
  ```

### **Retrieve All Tasks**
- **GET** `/api/tasks/all`
- **Optional parameter:** `sort=date` (sort by date)
- **Response:**
  ```json
  [
    {
      "id": 1,
      "title": "New Task",
      "description": "Task Description",
      "status": "NEW",
      "createdAt": "2023-01-01T12:00:00",
      "updatedAt": "2023-01-01T12:00:00"
    }
  ]
  ```

### **Retrieve Tasks by Status**
- **GET** `/api/tasks?status=new`
- **Parameters:** `status` (`new`, `in_progress`, `completed`)
- **Response:** List of tasks with the specified status.

### **Retrieve Task by ID**
- **GET** `/api/tasks/{id}`
- **Response:**
  ```json
  {
    "id": 1,
    "title": "New Task",
    "description": "Task Description",
    "status": "NEW",
    "createdAt": "2023-01-01T12:00:00",
    "updatedAt": "2023-01-01T12:00:00"
  }
  ```

### **Update a Task**
- **PUT** `/api/tasks/{id}`
- **Body (JSON):**
  ```json
  {
    "title": "Updated Task",
    "description": "New Description",
    "status": "IN_PROGRESS"
  }
  ```
- **Response:** Updated task.

### **Delete a Task**
- **DELETE** `/api/tasks/{id}`
- **Response:**
    - **204 No Content**: Task deleted.
    - **404 Not Found**: Task does not exist.

## API Testing

You can test the API using tools like:
- [Postman](https://www.postman.com/)
- [cURL](https://curl.se/)

## Author

- **Name**: Bartek Pokrywka
- **Email**: bpmcircuits@gmail.com
