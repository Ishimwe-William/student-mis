package com.bunsen.studentmis.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.UUID;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID teacher_id;
    private String name;
    private String dob;
    private String gender;
    @Enumerated(EnumType.STRING)
    private EQualification degree;
    @Column(unique = true)
    private String email;
    private String phone;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String created_at;

    public Teacher() {
        this.teacher_id=UUID.randomUUID();
        this.created_at= String.valueOf(new Date());
    }

    public UUID getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(UUID teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public EQualification getDegree() {
        return degree;
    }

    public void setDegree(EQualification degree) {
        this.degree = degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", degree='" + degree + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
