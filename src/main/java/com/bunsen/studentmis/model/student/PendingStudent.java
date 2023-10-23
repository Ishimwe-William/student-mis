package com.bunsen.studentmis.model.student;

import com.bunsen.studentmis.model.ERegistrationStatus;
import com.bunsen.studentmis.model.academicUnit.Department;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
public class PendingStudent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String parent;
    private String dob;
    private String parPhone;
    @OneToOne
    private Department department;
    private String invite;
    private String likes;
    private String message;
    @Enumerated(EnumType.STRING)
    private ERegistrationStatus registrationStatus;

    public PendingStudent() {
    }

    public ERegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(ERegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String[] getLikesArray() {
        return likes != null ? likes.split(",") : new String[0];
    }

    public void setLikesArray(String[] likesArray) {
        this.likes = String.join(",", likesArray);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getParPhone() {
        return parPhone;
    }

    public void setParPhone(String parPhone) {
        this.parPhone = parPhone;
    }


    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PendingStudent{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", parent='" + parent + '\'' +
                ", dob='" + dob + '\'' +
                ", parPhone='" + parPhone + '\'' +
                ", department=" + department +
                ", invite='" + invite + '\'' +
                ", likes='" + likes + '\'' +
                ", message='" + message + '\'' +
                ", registrationStatus=" + registrationStatus +
                '}';
    }
}