package com.co.hansolo.tecnology.repositories;

import com.co.hansolo.tecnology.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    //BUSCA UN EMPLEADO POR EMAIL
    @Query(value = "select * from employees where email=?1",nativeQuery = true)
    public abstract Employee findByEmail (String email);

    //LISTA TODOS LOS EMPLEADOS DE UNA EMPRESA
    @Query(value = "select * from employees where enterprise_id=?1",nativeQuery = true)
    public abstract ArrayList<Employee> getAllByEnterprise (Long id);



}
