package com.example.prog3.Repository.last2;

import com.example.prog3.model.last2.Cnaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CnapsRepository extends JpaRepository<Cnaps,Long> {
    Cnaps findByEndToEndId(Long endToEndId);
    Cnaps findByMatricule(String matricule);
}
