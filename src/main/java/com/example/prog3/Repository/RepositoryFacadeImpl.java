package com.example.prog3.Repository;

import com.example.prog3.Repository.last1.EmployeeRepository;
import com.example.prog3.Repository.last2.CnapsRepository;
import com.example.prog3.model.last1.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@Transactional
public class RepositoryFacadeImpl implements RepositoryFacade {
    private final EmployeeRepository employeeRepository;
    private final CnapsRepository cnapsRepository;

    @Override
    public Employee findByMatricule(String matricule) {
        Employee first = employeeRepository.findByMatricule(matricule);
        first.setCnaps(cnapsRepository.findByEndToEndId(first.getId()).getCnaps());
        return first;
    }
}
