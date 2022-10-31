package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Employee;
import com.co.hansolo.tecnology.services.IEmployeeService;
import com.co.hansolo.tecnology.services.IEnterpriseService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeFrontController {

    private final IEmployeeService employeeService;
    private final IEnterpriseService enterpriseService;

    public EmployeeFrontController(IEmployeeService employeeService, IEnterpriseService enterpriseService) {
        this.employeeService = employeeService;
        this.enterpriseService = enterpriseService;
    }

    //LISTAR EMPLEADOS
    @GetMapping(value = "/employee")
    public String findAll(Model model){
        model.addAttribute("listEmployee", this.employeeService.findAll());
        return "employee";
    }


    //CREAR NUEVO EMPLEADO
    @GetMapping(value = "/employee/crear")
    public String formCreate(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("listEnterprises", enterpriseService.findAll());
        return "new-employee";
    }

    @PostMapping(value = "/employee")
    public String formSave(@ModelAttribute("employee") Employee employee){
        String passCriptada=passwordEncoder().encode(employee.getPassword());
        employee.setPassword(passCriptada);
        employeeService.create(employee);
        return "redirect:/employee";
    }

    //UPDATE EMPLEADO
    @GetMapping(value = "/employee/editar/{id}")
    public String formUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("listEnterprises", enterpriseService.findAll());
        return "update-employee";
    }

    @PostMapping(value = "/employee/{id}")
    public String updateEmployee(@PathVariable Long id,@ModelAttribute("employee") Employee employee, Model model){
        String oldPass = this.employeeService.findById(id).getPassword();
        Employee employeeDTO= employeeService.findById(id);
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setEnterprise(employee.getEnterprise());
        employeeDTO.setRole(employee.getRole());
        employeeDTO.setState(employee.getState());
        if (!employeeDTO.getPassword().equals(employee.getPassword())){
        String passCriptada=passwordEncoder().encode(employee.getPassword());
        employeeDTO.setPassword(passCriptada);}
        employeeService.update(employeeDTO);
        return "redirect:/employee";
    }

    // EMPLEADOS POR EMPRESA
    @GetMapping(value = "/enterprises/{id}/employees")
    public String employeeByEnterprise(@PathVariable ("id") Long id, Model model){
        List<Employee> listEmployee = this.employeeService.getAllByEnterprise(id);
        model.addAttribute("listEmployee", listEmployee);

        return "/employee";
    }

    //ELIMINAR EMPLEADO
    @GetMapping(value = "/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
        return "redirect:/employee";
    }

    // ENCRYPTION DE CONTRASEÑAS
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "/accessDenied";
    }
}
