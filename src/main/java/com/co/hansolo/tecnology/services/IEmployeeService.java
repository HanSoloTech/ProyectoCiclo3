package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Employee;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeService {

    // TRAE UNA LISTA DE TODOS LOS EMPLEADOS EXISTENTES
    public List<Employee> findAll();

    // TRAE UNA LISTA DE TODOS LOS EMPLEADOS DE UNA EMPRESA
    ArrayList<Employee> getAllByEnterprise(Long id);

    // BUSCA UN EMPLEADO POR EL ID
    public Employee findById(Long id);

    // CREA UN EMPLEADO
    public Employee create(Employee employee);

    // ACTUALIZA UN EMPLEADO POR EL ID
    public Employee update(Long id, Employee employee);

    // ACTUALIZA UN EMPLEADO
    public Employee update(Employee employee);

    // ELIMINA UN EMPLEADO
    public void deleteById(Long id);

    // BUSCA UN EMPLEADO POR EL EMAIL
    public Employee getEmployee(String email)throws Exception;




}
