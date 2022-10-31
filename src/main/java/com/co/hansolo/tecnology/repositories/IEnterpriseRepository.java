package com.co.hansolo.tecnology.repositories;

import com.co.hansolo.tecnology.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnterpriseRepository extends JpaRepository<Enterprise,Long> {
}
