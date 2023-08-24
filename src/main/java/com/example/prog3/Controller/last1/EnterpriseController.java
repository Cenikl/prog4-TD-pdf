package com.example.prog3.Controller.last1;

import com.example.prog3.Controller.TokenController;
import com.example.prog3.Service.last1.EnterpriseService;
import com.example.prog3.Service.last1.PhoneService;
import com.example.prog3.model.last1.Enterprise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class EnterpriseController extends TokenController {
    private EnterpriseService enterpriseService;
    private PhoneService phoneService;

    @GetMapping("/enterprise")
    public String formEnterprise(@ModelAttribute Enterprise enterprise, Model model){
        model.addAttribute("enterprise",enterpriseService.getEnterprise());
        return "updateEnterprise";
    }

    @PostMapping("/updateEnt")
    public String upEmployee(
            @RequestParam("Img") MultipartFile file,
            @RequestParam("name") String name ,
            @RequestParam("desc") String desc,
            @RequestParam("slogan") String slogan,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("nif") String nif,
            @RequestParam("stat") String stat,
            @RequestParam("rcs") String rcs,
            @RequestParam("cCode") String cCode,
            @RequestParam("phoneNumbers") String phoneNumbers) throws IOException {
        byte[] fileData = file.getBytes();
        enterpriseService.updateEnterprise(name, desc, slogan, address, email, nif, stat, rcs,fileData);
        phoneService.updatePhoneNumberEnterprise(cCode,phoneNumbers,enterpriseService.getEnterprise());
        return "redirect:/index";
    }
}
