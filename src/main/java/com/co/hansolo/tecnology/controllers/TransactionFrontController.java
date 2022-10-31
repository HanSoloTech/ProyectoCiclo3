package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Transaction;
import com.co.hansolo.tecnology.services.IEmployeeService;
import com.co.hansolo.tecnology.services.IEnterpriseService;
import com.co.hansolo.tecnology.services.ITransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TransactionFrontController {
    private final ITransactionService transactionService;
    private final IEnterpriseService enterpriseService;
    private final IEmployeeService employeeService;

    public TransactionFrontController(ITransactionService transactionService, IEnterpriseService enterpriseService,
                                      IEmployeeService employeeService) {
        this.transactionService = transactionService;
        this.enterpriseService = enterpriseService;
        this.employeeService = employeeService;
    }

    //LISTA TODAS LAS TRANSACCIONES EXISTENTES
    @GetMapping("/movements")
    public String findAll(Model model){
        model.addAttribute("movements", transactionService.findAll());
        Long total = this.transactionService.total();
        model.addAttribute("total",total);
        return "transactions";
    }

    //TRAE TODAS LAS TRANSACCIONES DE UNA EMPRESA Y EL TOTAL DE LAS MISMAS
    @GetMapping("/enterprises/movements/{id}")
    public String getAllByEnterprise(@PathVariable Long id, Model model){
        List<Transaction> transactions = this.transactionService.findAllByEnterprise(id);
        model.addAttribute("movements",transactions );
        Long total = this.transactionService.totalAmount(id);
        model.addAttribute("total", total);
        return "transactions";
    }

    //CREAR MOVIMIENTO
    @GetMapping("/transaction/crear")
    public String formCreate(Model model){
        Transaction transaction= new Transaction();
        model.addAttribute("transaction",transaction);
        model.addAttribute("employees",this.employeeService.findAll());
        model.addAttribute("enterprises",this.enterpriseService.findAll());
        return "new-transaction";

    }

    @PostMapping("/transaction")
    public String formSave(@ModelAttribute("transaction") Transaction transaction){
        this.transactionService.create(transaction);
        return "redirect:/transactions";
    }

    //EDITAR MOVIMIENTO
    @GetMapping("/transaction/editar/{id}")
    public String formUpdate(@PathVariable Long id, Model model){
        model.addAttribute("transaction",this.transactionService.findById(id));
        model.addAttribute("employees",this.employeeService.findAll());
        model.addAttribute("enterprises",this.enterpriseService.findAll());
        return "update-transaction";
    }

    @PostMapping("/transaction/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute("transaction")Transaction transaction, Model model){
        Transaction transactionDTO = this.transactionService.findById(id);
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setConcept(transaction.getConcept());
        transactionDTO.setEmployee(transaction.getEmployee());

        this.transactionService.update(transactionDTO);
        return "redirect:/movements";
    }

    //ELIMINAR MOVIMIENTO
    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable Long id){
        this.transactionService.deleteById(id);
        return "redirect:/movements";
    }
}
