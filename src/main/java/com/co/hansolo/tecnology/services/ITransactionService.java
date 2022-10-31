package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Enterprise;
import com.co.hansolo.tecnology.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ITransactionService {

    // LISTA TODAS LAS TRANSACCIONES DE UNA EMPRESA
    public ArrayList<Transaction> findAllByEnterprise(Long id);

    //LISTA TODAS LAS TRANSACCIONES EXISTENTES
    public List<Transaction> findAll();

    // TRAE EL MONTO TOTAL DE TODAS LAS TRANSACCIONES DE UNA EMPRESA
    public Long totalAmount(Long id);

    // TRAE EL MONTO TOTAL DE TODAS LAS TRANSFERENCIAS EXISTENTES
    public Long total();

    // BUSCA UNA TRANSACCION ESPECIFICA POR EL ID
    public Transaction findById(Long id);

    // TRAE EL ID DE UN EMPLEADO DE LA TABLA EMPLEADO A PARTIR DE SU CORREO
    public Long findByEmail(String email);

    // CREA UNA TRANSACCION
    public Transaction create(Transaction transaction);

    public Transaction update(Long id, Transaction transaction);

    public Transaction update (Transaction transaction);

    public void deleteById(Long id);


}
