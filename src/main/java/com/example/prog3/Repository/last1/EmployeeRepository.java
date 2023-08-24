package com.example.prog3.Repository.last1;

import com.example.prog3.model.last1.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByMatricule(String matricule);
    Employee findByEmailPro(String emailPro);

    List<Employee> findBySex(Employee.Sex sex);
    List<Employee> findByRoleContainingIgnoreCase(String role);
    @Query(value = "select distinct * from employee "+
            "where ( cast(:startDate as date) is null or employement_date >= :startDate) "+
            "and ( cast(:endDate as date) is null or departure_date <= :endDate)", nativeQuery = true)
    List<Employee> findEmployeesByDateRange(LocalDate startDate,LocalDate endDate);

    @Query(value = "select distinct e.* from employee e "+
            "JOIN phone p on e.id = p.phone_employee " +
            "where (:countryCode is null or p.country_code = :countryCode) ", nativeQuery = true)
    List<Employee> findEmployeesByCountryCode(String countryCode);
}
