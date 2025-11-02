package com.example.courseservice.service;

import com.example.courseservice.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {
    
    private final ConcurrentHashMap<Long, Course> courses = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public CourseService() {
        // Add some sample data
        List<Long> studentIds1 = new ArrayList<>();
        studentIds1.add(1L);
        courses.put(idGenerator.getAndIncrement(), new Course(1L, "Mathematics", "Basic Mathematics Course", studentIds1));
        
        List<Long> studentIds2 = new ArrayList<>();
        studentIds2.add(2L);
        courses.put(idGenerator.getAndIncrement(), new Course(2L, "Physics", "Introduction to Physics", studentIds2));
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public Course getCourseById(Long id) {
        return courses.get(id);
    }

    public Course createCourse(Course course) {
        Long id = idGenerator.getAndIncrement();
        course.setId(id);
        courses.put(id, course);
        return course;
    }

    public Course updateCourse(Long id, Course courseDetails) {
        if (!courses.containsKey(id)) {
            return null;
        }
        
        Course course = courses.get(id);
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setStudentIds(courseDetails.getStudentIds());
        courses.put(id, course);
        return course;
    }

    public boolean deleteCourse(Long id) {
        return courses.remove(id) != null;
    }
}