package com.co.hansolo.tecnology.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "ENTERPRISES")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String NIT;
    private String phone;
    private String address;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Enterprise() { }

    public Enterprise(String name, String NIT, String phone, String address, LocalDate createAt, LocalDate updateAt) {
        Name = name;
        this.NIT = NIT;
        this.phone = phone;
        this.address = address;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", NIT='" + NIT + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
