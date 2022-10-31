package com.co.hansolo.tecnology.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    private String password;

    private String role;

    private Boolean state;
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    private LocalDate createAt;
    private LocalDate updateAt;

    public Employee() { }

    public Employee(Long id, String name, String email, String password, String role, Boolean state, Enterprise enterprise,
                     LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.state = state;
        this.enterprise = enterprise;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + state +
                ", enterprise=" + enterprise +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
