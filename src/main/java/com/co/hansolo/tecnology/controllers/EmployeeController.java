package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Employee;
import com.co.hansolo.tecnology.services.IEmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class EmployeeController {
    private final IEmployeeService service;

    public EmployeeController(IEmployeeService service) { this.service = service; }

    // LISTA TODOS LOS EMPLEADOS EXISTENTES
    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> findAll(){return ResponseEntity.ok().body(this.service.findAll());}

    // LISTA LOS EMPLEADOS DE UNA EMPRESA ESPECIFICA
    @GetMapping(path = "/enterprises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Employee> findAllByEnterprise(@PathVariable Long id){
        return this.service.getAllByEnterprise(id);
    }

    // BUSCA UN EMPLEADO POR ID
    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee  findById(@PathVariable Long id){return this.service.findById(id);}

    // CREA UN EMPLEADO
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee){return this.service.create(employee);}

    // ACTUALIZA LOS DATOS DE UN EMPLEADO
    @PatchMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Long id){
        return ResponseEntity.ok().body(this.service.update(id,employee));
    }

    // ELIMINA UN EMPLEADO SI EXISTE
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){this.service.deleteById(id);}




}
