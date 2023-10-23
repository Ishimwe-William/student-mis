package com.bunsen.studentmis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CourseDefinition {
    @Id
    private UUID course_def_id;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;

    public CourseDefinition() {
        this.course_def_id=UUID.randomUUID();
    }

    public UUID getCourse_def_id() {
        return course_def_id;
    }

    public void setCourse_def_id(UUID course_def_id) {
        this.course_def_id = course_def_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "CourseDefinition{" +
                "course_def_id=" + course_def_id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
