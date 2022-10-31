package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Employee;
import com.co.hansolo.tecnology.repositories.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImplService implements IEmployeeService{

    private final IEmployeeRepository repository;

    LocalDate today = LocalDate.now();
    public EmployeeImplService(IEmployeeRepository repository) { this.repository = repository; }

    @Override
    public List<Employee> findAll() { return repository.findAll(); }


    @Override
    public ArrayList<Employee> getAllByEnterprise(Long id) {
        return this.repository.getAllByEnterprise(id);
    }


    @Override
    public Employee findById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Employee create(Employee employee) {
        employee.setCreateAt(today);
        return repository.save(employee); }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeDB = this.findById(id);
        employeeDB.setName(employee.getName());
        employeeDB.setEmail(employee.getEmail());
        employeeDB.setRole(employee.getRole());
        employeeDB.setState(employee.getState());
        employeeDB.setUpdateAt(today);

        return repository.save(employeeDB);
    }

    @Override
    public Employee update(Employee employee) {
        employee.setUpdateAt(today);
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id);}

    @Override
    public Employee getEmployee(String email) throws Exception {
        Optional<Employee> optionalEmployee = Optional.ofNullable(repository.findByEmail(email));
        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }else {
            throw new Exception("El Empleado Buscado no Existe");
        }
    }
}
