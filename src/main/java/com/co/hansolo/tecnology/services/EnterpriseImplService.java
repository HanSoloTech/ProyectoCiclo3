package com.co.hansolo.tecnology.services;

import com.co.hansolo.tecnology.models.Enterprise;
import com.co.hansolo.tecnology.repositories.IEnterpriseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class EnterpriseImplService implements IEnterpriseService{

    LocalDate today = LocalDate.now();
    private final IEnterpriseRepository repository;

    public EnterpriseImplService(IEnterpriseRepository repository) { this.repository = repository; }

    @Override
    public List<Enterprise> findAll() { return repository.findAll(); }

    @Override
    public Enterprise findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Enterprise create(Enterprise enterprise) {
        enterprise.setCreateAt(today);
        return repository.save(enterprise);
    }

    @Override
    public Enterprise update(Long id, Enterprise enterprise) {
        Enterprise enterpriseDB = this.findById(id);
        enterpriseDB.setName(enterprise.getName());
        enterpriseDB.setNIT(enterprise.getNIT());
        enterpriseDB.setPhone(enterprise.getPhone());
        enterpriseDB.setAddress(enterprise.getAddress());
        enterpriseDB.setUpdateAt(today);
        return repository.save(enterpriseDB);
    }

    @Override
    public Enterprise update(Enterprise enterprise) {
        enterprise.setUpdateAt(today);
        return repository.save(enterprise);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {repository.deleteById(id);

    }
}
