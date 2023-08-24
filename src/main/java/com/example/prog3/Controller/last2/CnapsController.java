package com.example.prog3.Controller.last2;

import com.example.prog3.Controller.TokenController;
import com.example.prog3.Service.last1.EmployeeService;
import com.example.prog3.Service.last1.EnterpriseService;
import com.example.prog3.Service.last1.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CnapsController extends TokenController {
    private final EmployeeService employeeService;
    private final PhoneService phoneService;
    private final EnterpriseService enterpriseService;

}
