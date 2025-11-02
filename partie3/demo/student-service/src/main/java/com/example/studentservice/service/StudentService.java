package com.example.studentservice.service;

import com.example.studentservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    
    private final ConcurrentHashMap<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentService() {
        // Add some sample data
        students.put(idGenerator.getAndIncrement(), new Student(1L, "John Doe", "john.doe@example.com"));
        students.put(idGenerator.getAndIncrement(), new Student(2L, "Jane Smith", "jane.smith@example.com"));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public Student getStudentById(Long id) {
        return students.get(id);
    }

    public Student createStudent(Student student) {
        Long id = idGenerator.getAndIncrement();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public Student updateStudent(Long id, Student studentDetails) {
        if (!students.containsKey(id)) {
            return null;
        }
        
        Student student = students.get(id);
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        students.put(id, student);
        return student;
    }

    public boolean deleteStudent(Long id) {
        return students.remove(id) != null;
    }
}