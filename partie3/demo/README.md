# Microservices Demo

This project contains two independent Spring Boot microservices:

## Student Service
- Port: 8081
- Provides CRUD operations for students
- Stores data in memory

## Course Service
- Port: 8082
- Provides CRUD operations for courses
- Can communicate with the Student Service via REST

## How to Run

1. Open two terminal windows
2. In the first terminal, navigate to the `student-service` directory and run:
   ```
   ./mvnw spring-boot:run
   ```
3. In the second terminal, navigate to the `course-service` directory and run:
   ```
   ./mvnw spring-boot:run
   ```

## API Endpoints

### Student Service (http://localhost:8081)
- GET /students - Get all students
- GET /students/{id} - Get a specific student
- POST /students - Create a new student
- PUT /students/{id} - Update a student
- DELETE /students/{id} - Delete a student

### Course Service (http://localhost:8082)
- GET /courses - Get all courses
- GET /courses/{id} - Get a specific course
- POST /courses - Create a new course
- PUT /courses/{id} - Update a course
- DELETE /courses/{id} - Delete a course
- GET /courses/{id}/students - Get students enrolled in a course (communicates with Student Service)