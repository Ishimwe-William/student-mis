package com.bunsen.studentmis.model;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String parent;
    private String dob;
    private String parPhone;
    private String major;
    private String invite;
    private String likes;
    private String message;
    @Enumerated(EnumType.STRING)
    private ERegistrationStatus registrationStatus;

    public Student() {
    }

    public ERegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(ERegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public void clear(){
        this.setLikesArray(null);
        this.setMessage("");
        this.setInvite("");
        this.setMajor("");
        this.setParent("");
        this.setPhone("");
        this.setParPhone("");
        this.setEmail("");
        this.setFullName("");
    }
}