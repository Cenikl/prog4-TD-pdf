package com.example.prog3;

import com.example.prog3.Repository.last1.EmployeeRepository;
import com.example.prog3.Repository.last2.CnapsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class CnapsIT {
    @Autowired
    private CnapsRepository cnapsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional("last1TransactionManager")
    public void whenCreatingEmployee(){

    }
}