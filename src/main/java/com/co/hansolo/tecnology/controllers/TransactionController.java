package com.co.hansolo.tecnology.controllers;


import com.co.hansolo.tecnology.models.Transaction;
import com.co.hansolo.tecnology.services.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final ITransactionService service;

    public TransactionController(ITransactionService service) { this.service = service; }

    // LISTA TODAS LAS TRANSACCIONES EXISTENTES
    @GetMapping(path = "/movement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

    // LISTA TODAS LAS TRANSACCIONES POR EMPRESA
    @GetMapping(path = "enterprise/{id}/movements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getByEnterprise(@PathVariable Long id){
        return ResponseEntity.ok().body(this.service.findAllByEnterprise(id));
    }

    // BUSCAR TRANSACCION POR ID
    @GetMapping(path = "/movement/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transaction  findById(@PathVariable  Long id){return this.service.findById(id); }

    // TRAE EL MONTO TOTAL DE LAS TRANSACCIONES DE UNA EMPRESA
    @GetMapping("/enterprise/{id}/total")
    public Long totalAmount(@PathVariable Long id){return this.service.totalAmount(id);}

    // CREAR TRANSACCION
    @PostMapping(path = "movement/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> create(@RequestBody Transaction Transaction){
        return new ResponseEntity<Transaction>(this.service.create(Transaction), HttpStatus.OK);
    }

    //ACTUALIZAR TRANSACCION
    @PatchMapping(path = "movement/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction, @PathVariable Long id) {
        return ResponseEntity.ok().body( this.service.update (transaction));
    }



    // ELIMINAR UNA TRANSACCION
    @DeleteMapping(path = "movement/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {this.service.deleteById(id);}

}
