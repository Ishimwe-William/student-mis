package com.bunsen.studentmis.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
public class AcademicUnit {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(unique = true)
    private String code;
    private String name;

    @Enumerated(EnumType.STRING)
    private EAcademicUnit unit;

    @ManyToOne
    @JoinColumn
    private AcademicUnit parent;

    @OneToMany(mappedBy = "parent")
    private List<AcademicUnit> children;

    public AcademicUnit() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public EAcademicUnit getUnit() {
        return unit;
    }

    public void setUnit(EAcademicUnit unit) {
        this.unit = unit;
    }

    public AcademicUnit getParent() {
        return parent;
    }

    public void setParent(AcademicUnit parent) {
        this.parent = parent;
    }

    public List<AcademicUnit> getChildren() {
        return children;
    }

    public void setChildren(List<AcademicUnit> children) {
        this.children = children;
    }
}
