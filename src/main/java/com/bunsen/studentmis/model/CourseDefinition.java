package com.bunsen.studentmis.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class CourseDefinition {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID course_def_id;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "courseDefinitions", fetch = FetchType.EAGER)
    private Set<Teacher> teachers;

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getCourseDefinitions().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getCourseDefinitions().remove(this);
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

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
