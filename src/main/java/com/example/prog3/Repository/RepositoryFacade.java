package com.example.prog3.Repository;

import com.example.prog3.model.last1.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RepositoryFacade {
    Employee findByMatricule(String matricule);
}
