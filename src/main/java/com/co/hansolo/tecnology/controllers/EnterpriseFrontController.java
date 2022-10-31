package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Enterprise;
import com.co.hansolo.tecnology.services.IEnterpriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnterpriseFrontController {
    private final IEnterpriseService enterpriseService;

    public EnterpriseFrontController(IEnterpriseService enterpriseService) { this.enterpriseService = enterpriseService; }

    //LISTA TODAS LAS EMPRESAS EXISTENTES
    @GetMapping("/enterprises")
    public String findAll(Model model){
        model.addAttribute("enterprises", enterpriseService.findAll());
        return "enterprises";
    }

    //CREAR UNA EMPRESA
    @GetMapping("/enterprises/crear")
    public String formCreate(Model model){
        Enterprise enterprise = new Enterprise();
        model.addAttribute("enterprise", enterprise);
        return "new-enterprise";
    }

    @PostMapping("/enterprises")
    public String formSave(@ModelAttribute("enterprise") Enterprise enterprise){
        enterpriseService.create(enterprise);
        return "redirect:/enterprises/";
    }

    //ACTUALIZA UNA EMPRESA
    @GetMapping("enterprises/editar/{id}")
    public String formUpdate(@PathVariable Long id, Model model){
        model.addAttribute("enterprise",enterpriseService.findById(id));
        return "update-enterprise";
    }

    @PostMapping("/enterprises/{id}")
    public String updateEnterprise(@PathVariable Long id, @ModelAttribute("enterprise") Enterprise enterprise, Model model){
        Enterprise enterpriseDTO = enterpriseService.findById(id);
        enterpriseDTO.setName(enterprise.getName());
        enterpriseDTO.setNIT(enterprise.getNIT());
        enterpriseDTO.setAddress(enterprise.getAddress());
        enterpriseDTO.setPhone(enterprise.getPhone());
        enterpriseService.update(enterpriseDTO);
        return "redirect:/enterprises";
    }

    //ELIMINA UNA EMPRESA
    @GetMapping("/enterprises/borrar/{id}")
    public String deleteEnterprise(@PathVariable Long id){
        enterpriseService.deleteById(id);
        return "redirect:/enterprises";
    }
}
