package com.example.prog3.Controller.last1;

import com.example.prog3.Controller.TokenController;
import com.example.prog3.Enum.Age;
import com.example.prog3.Service.last1.EmployeeService;
import com.example.prog3.Service.last1.EnterpriseService;
import com.example.prog3.Service.last1.PhoneService;
import com.example.prog3.Service.last2.CnapsService;
import com.example.prog3.dto.EmployeeDetails;
import com.example.prog3.model.last1.Employee;
import com.example.prog3.model.last1.Enterprise;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController extends TokenController {

    private final EmployeeService employeeService;
    private final PhoneService phoneService;
    private final EnterpriseService enterpriseService;
    private final CnapsService cnapsService;
    private final TemplateEngine templateEngine;


    @GetMapping("/index")
    public String index(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "lastName",required = false) String lastName,
            @RequestParam(value = "gender",required = false) String sex,
            @RequestParam(value = "role",required = false) String role,
            @RequestParam(value = "cCode",required = false) String cCode,
            @RequestParam(value = "employementDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eDate,
            @RequestParam(value = "departureDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dDate,
            @RequestParam(value = "sort",required = false) String sort,
            Model model,HttpServletRequest request){
        Enterprise enterprise = enterpriseService.getEnterprise();
        List<Employee> employeeList = employeeService.getFilteredEmployees(name,lastName,sex,role,eDate,dDate,cCode,sort);
        model.addAttribute("employees",employeeList);
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("request", request);
        return "index";
    }

    @GetMapping("/index/exportCsv")
    public void getCsv(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "lastName",required = false) String lastName,
            @RequestParam(value = "gender",required = false) String sex,
            @RequestParam(value = "role",required = false) String role,
            @RequestParam(value = "cCode",required = false) String cCode,
            @RequestParam(value = "employementDate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eDate,
            @RequestParam(value = "departureDate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dDate,
            @RequestParam(value = "sort",required = false) String sort,
            HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeService.getFilteredEmployees(name,lastName,sex,role,eDate,dDate,cCode,sort);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename\"employees.csv\"");
        employeeService.exportToCsv(employees,phoneService,response);
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(
            @RequestParam("testImg") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
            @RequestParam("gender") String sex,
            @RequestParam("csp") String csp,
            @RequestParam("address") String address,
            @RequestParam("emailPro") String emailPro,
            @RequestParam("emailPerso") String emailPerso,
            @RequestParam("role") String role,
            @RequestParam("child") Integer child,
            @RequestParam("employementDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eDate,
            @RequestParam("departureDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dDate,
            @RequestParam(value = "cCode",required = false) String cCode,
            @RequestParam("phoneNumbers") String phoneNumbers,
            @RequestParam("cinNumber") String cinNumber) throws IOException {
        byte[] filing = file.getBytes();
        String fileData = Base64.encodeBase64String(filing);
        String[] numberList = phoneNumbers.split(",");
        for (String phoneNumber : numberList) {
            if(phoneNumber.length() != 10){
                throw new IllegalArgumentException("Phone number need to be exactly 10");
            }
        }
        String cnaps = "";
        employeeService.createEmployee(name,lastName,birthDate,sex,csp,address,emailPro,emailPerso,role,child,eDate,dDate,cnaps,cinNumber,fileData);
        phoneService.createPhoneNumberEmployee(cCode,phoneNumbers,employeeService.getByEmailPro(emailPro));
        cnapsService.createEmployee(name,lastName,birthDate,sex,csp,address,emailPro,emailPerso,role,child,eDate,dDate,cnaps,cinNumber,fileData,employeeService.getByEmailPro(emailPro).getId());
        return "redirect:/index";
    }

    @GetMapping("/form")
    public String form(@ModelAttribute Employee employee, Model model){
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @GetMapping("/updateEmployee/{matricule}")
    public String updateEmployee(@PathVariable String matricule,Model model){
        model.addAttribute("employee",employeeService.getByMatriculeWithCnaps(matricule));
        return "updateEmployee";
    }

    @GetMapping("/formEmployee/{matricule}")
    public String formEmployee(@PathVariable String matricule,Model model){
        Enterprise enterprise = enterpriseService.getEnterprise();
        model.addAttribute("employee",employeeService.getByMatriculeWithCnaps(matricule));
        model.addAttribute("enterprise",enterprise);
        return "formEmployee";
    }

    @GetMapping("/fiche/{matricule}")
    public String fiche(@PathVariable String matricule,Model model){
        Enterprise enterprise = enterpriseService.getEnterprise();
        model.addAttribute("employee",employeeService.showEmployeeInfoBirthday(matricule));
        model.addAttribute("enterprise",enterprise);
        return "fiche";
    }
    @GetMapping("/fiche/pdf/{matricule}")
    public String downloadPdf(
            @RequestParam(value = "Age",defaultValue = "BIRTHDAY") String ageEnum,
            @PathVariable String matricule) throws IOException, DocumentException {
        Enterprise enterprise = enterpriseService.getEnterprise();
        EmployeeDetails employeeDetails = employeeService.showEmployeeInfoBirthday(matricule);
            Context context = new Context();
            context.setVariable("employee",employeeDetails);
            context.setVariable("enterprise",enterprise);
            employeeService.generatePdfFromHtml(
                    templateEngine.process("fiche", context),
                    employeeDetails.getMatricule());
        return "redirect:/index";
    }
    @GetMapping("/fichee/pdf/{matricule}")
    public String downloadPdef(
            @RequestParam(value = "Age",defaultValue = "YEAR_ONLY") String ageEnum,
            @PathVariable String matricule) throws IOException, DocumentException {
        Enterprise enterprise = enterpriseService.getEnterprise();
            EmployeeDetails employeeDetails = employeeService.showEmployeeInfoYear(matricule);
            Context context = new Context();
            context.setVariable("employee",employeeDetails);
            context.setVariable("enterprise",enterprise);
            employeeService.generatePdfFromHtml(
                    templateEngine.process("fiche", context),
                    employeeDetails.getMatricule());
        return "redirect:/index";
    }

    @PostMapping("/updateEmp/{matricule}")
    public String upEmployee(
            @PathVariable String matricule,
            @RequestParam("Img") MultipartFile file,
            @RequestParam("name") String name ,
            @RequestParam("lastName") String lastName,
            @RequestParam("gender") String sex,
            @RequestParam("birthDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
            @RequestParam("csp") String csp,
            @RequestParam("address") String address,
            @RequestParam("emailPro") String emailPro,
            @RequestParam("emailPerso") String emailPerso,
            @RequestParam("role") String role,
            @RequestParam("child") Integer child,
            @RequestParam("employementDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eDate,
            @RequestParam("departureDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dDate,
            @RequestParam("cCode") String cCode,
            @RequestParam("phoneNumbers") String phoneNumbers,
            @RequestParam("cinNumber") String cinNumber) throws IOException {
                byte[] filing = file.getBytes();
                String fileData = Base64.encodeBase64String(filing);
                String[] numberList = phoneNumbers.split(",");
                for (String phoneNumber : numberList) {
                    if(phoneNumber.length() != 10){
                        throw new IllegalArgumentException("Phone number need to be exactly 10");
                    }
                }
                employeeService.crupdateEmployee(matricule,name,lastName,birthDate,sex,csp,address,emailPro,emailPerso,role,child,eDate,dDate,cinNumber,fileData);
                phoneService.updatePhoneNumber(cCode,phoneNumbers,employeeService.getByMatricule(matricule));
                cnapsService.crupdateEmployee(matricule,name,lastName,birthDate,sex,csp,address,emailPro,emailPerso,role,child,eDate,dDate,cinNumber,fileData);
        return "redirect:/index";
    }
}
