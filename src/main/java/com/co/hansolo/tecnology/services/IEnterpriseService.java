package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Enterprise;

import java.util.List;

public interface IEnterpriseService {

    // LISTA TODAS LAS EMPRESAS EXISTENTES
    public List<Enterprise> findAll();

    //BUSCA UNA EMPRESA POR EL ID
    public Enterprise findById(Long id);

    // CREA UNA EMPRESA
    public Enterprise create(Enterprise enterprise);

    // ACTUALIZA LOS DATOS DE UNA EMPRESA
    public Enterprise update(Long id, Enterprise enterprise);

    public Enterprise update (Enterprise enterprise);

    // ELIMINA UNA EMPRESA
    public void deleteById(Long id);




}
