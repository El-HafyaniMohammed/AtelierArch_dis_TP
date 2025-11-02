package com.example.courseservice.model;

import java.util.List;

public class Course {
    private Long id;
    private String name;
    private String description;
    private List<Long> studentIds;

    public Course() {
    }

    public Course(Long id, String name, String description, List<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.studentIds = studentIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}