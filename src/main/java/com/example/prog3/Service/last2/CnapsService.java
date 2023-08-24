package com.example.prog3.Service.last2;

import com.example.prog3.Repository.last2.CnapsRepository;
import com.example.prog3.model.last1.Employee;
import com.example.prog3.model.last2.Cnaps;
import com.example.prog3.utils.MatriculeGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class CnapsService {
    private final CnapsRepository employeeRepository;

    public Cnaps createEmployee(String firstName, String lastName, LocalDate birthDate, String sex, String csp, String address, String emailPro, String emailPerso, String role, Integer child, LocalDate eDate, LocalDate dDate, String cnaps, String cin, byte[] emplImg,Long endToEndId) {
        Cnaps employee = new Cnaps();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthDate(birthDate);
        employee.setMatricule(MatriculeGenerator.generateMatricule(employeeRepository.findAll().size() == 0 ? 0 : employeeRepository.findAll().size()));
        employee.setEmplImg(emplImg);
        employee.setSex(Cnaps.Sex.valueOf(sex));
        employee.setCsp(Cnaps.Csp.valueOf(csp));
        employee.setAddress(address);
        employee.setEmailPro(emailPro);
        employee.setEmailPerso(emailPerso);
        employee.setRole(role);
        employee.setChild(child);
        employee.setEmployementDate(eDate);
        employee.setDepartureDate(dDate);
        employee.setCnaps(cnaps);
        employee.setCin(cin);
        employee.setEndToEndId(endToEndId);
        return employeeRepository.save(employee);
    };
    public Cnaps crupdateEmployee(String matricule, String name, String lastName, LocalDate birthDate, String sex, String csp, String address, String emailPro, String emailPerso, String role, Integer child, LocalDate eDate, LocalDate dDate, String cin, byte[] image){
        Cnaps employee = employeeRepository.findByMatricule(matricule);
        employee.setFirstName(name);
        employee.setLastName(lastName);
        employee.setBirthDate(birthDate);
        employee.setEmplImg(image);
        employee.setSex(Cnaps.Sex.valueOf(sex));
        employee.setCsp(Cnaps.Csp.valueOf(csp));
        employee.setAddress(address);
        employee.setEmailPro(emailPro);
        employee.setEmailPerso(emailPerso);
        employee.setRole(role);
        employee.setChild(child);
        employee.setEmployementDate(eDate);
        employee.setDepartureDate(dDate);
        employee.setCin(cin);
        return employeeRepository.save(employee);
    }
}
