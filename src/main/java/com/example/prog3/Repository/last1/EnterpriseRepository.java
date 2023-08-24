package com.example.prog3.Repository.last1;

import com.example.prog3.model.last1.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise,Long> {
}
