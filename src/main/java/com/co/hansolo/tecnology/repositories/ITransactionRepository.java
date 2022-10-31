package com.co.hansolo.tecnology.repositories;

import com.co.hansolo.tecnology.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;


@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    //LISTADO DE TRANSACCIONES POR EMPRESA

    @Query(value="select * from transactions where employee_id  in (select id from employees where enterprise_id = ?1)", nativeQuery = true)
    public abstract ArrayList<Transaction> findAllByEnterprise(Long id);

    //MONTO TOTAL DE LAS TRANSACCIONES DE UNA  EMPRESA
    @Query(value = "select sum(amount) from transactions where employee_id in (select id from employees where enterprise_id= ?1)", nativeQuery = true)
    public abstract Long totalAmount(Long id);

    // TRAE EL ID DE UN EMPLEADO DE LA TABLA EMPLEADO A PARTIR DE SU CORREO

    @Query(value="select id from employees where mail=?1", nativeQuery = true)
    public abstract Long findByEmail(String correo);

    // TOTAL DE TODAS LAS TRANSACCIONES
    @Query(value="select sum(amount) from transactions", nativeQuery = true)
    public abstract Long total();



}
