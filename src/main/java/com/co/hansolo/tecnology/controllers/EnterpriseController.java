package com.co.hansolo.tecnology.controllers;

import com.co.hansolo.tecnology.models.Enterprise;
import com.co.hansolo.tecnology.services.IEnterpriseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseController {
    private final IEnterpriseService service;

    public EnterpriseController(IEnterpriseService service) { this.service = service; }

    // LISTA TODAS LAS EMPRESAS EXISTENTES
    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Enterprise>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

    // BUSCAR EMPRESA POR ID
    @GetMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Enterprise  findById(@PathVariable Long id){return this.service.findById(id);
    }

    // CREAR EMPRESA
    @PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise Enterprise){
        return new ResponseEntity<Enterprise>(this.service.create(Enterprise), HttpStatus.OK);
    }

    //ACTUALIZAR EMPRESA
    @PatchMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> update(@RequestBody Enterprise Enterprise, @PathVariable Long id) {
        return ResponseEntity.ok().body( this.service.update (Enterprise));
    }

    // ELIMINAR UNA EMPLEADO
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {this.service.deleteById(id);}


}
