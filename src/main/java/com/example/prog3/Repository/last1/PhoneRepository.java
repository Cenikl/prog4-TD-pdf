package com.example.prog3.Repository.last1;

import com.example.prog3.model.last1.Employee;
import com.example.prog3.model.last1.Enterprise;
import com.example.prog3.model.last1.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    Phone findByPhoneEmployee(Employee employee);

    List<Phone> findPhonesByPhoneEmployee(Employee phoneEmployee);

    List<Phone> findPhonesByPhoneEnterprise(Enterprise enterprise);

}
