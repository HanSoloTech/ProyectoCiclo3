package com.co.hansolo.tecnology.models;



import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String concept;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate createAt;
    private LocalDate updateAt;

    public Transaction() { }

    public Transaction(String concept, Double amount, Employee employee, LocalDate createAt, LocalDate updateAt) {
        this.concept = concept;
        this.amount = amount;
        this.employee = employee;
        this.createAt = createAt;
        this.updateAt = updateAt;


    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getConcept() { return concept; }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", employee=" + employee +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
