package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.lang.StringBuilder;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/students")
    public Mono<ResponseEntity<String>> getStudentsForCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return Mono.just(ResponseEntity.notFound().build());
        }
        
        // Make REST call to student service to get student details
        WebClient webClient = webClientBuilder.build();
        
        StringBuilder result = new StringBuilder("Students enrolled in course: " + course.getName() + "\n");
        
        // For each student ID in the course, make a call to the student service
        if (course.getStudentIds() != null && !course.getStudentIds().isEmpty()) {
            for (Long studentId : course.getStudentIds()) {
                Mono<String> studentResponse = webClient.get()
                    .uri("http://localhost:8081/students/" + studentId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorReturn("Student with ID " + studentId + " not found");
                
                result.append("Student ID ").append(studentId).append(": ").append(studentResponse.block()).append("\n");
            }
        } else {
            result.append("No students enrolled in this course.");
        }
        
        return Mono.just(ResponseEntity.ok(result.toString()));
    }
}