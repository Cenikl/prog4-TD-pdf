package com.example.prog3.Service.last1;

import com.example.prog3.Repository.last1.EnterpriseRepository;
import com.example.prog3.model.last1.Enterprise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnterpriseService {
    private EnterpriseRepository repository;

    public Enterprise getEnterprise(){
        return repository.findAll().get(0);
    }
    public void updateEnterprise(
            String name ,
            String desc,
            String slogan,
            String address,
            String email,
            String nif,
            String stat,
            String rcs,
            byte[] logo){
        Enterprise enterprise = getEnterprise();
        enterprise.setName(name);
        enterprise.setDescription(desc);
        enterprise.setSlogan(slogan);
        enterprise.setAddress(address);
        enterprise.setEmail(email);
        enterprise.setNif(nif);
        enterprise.setStat(stat);
        enterprise.setRcs(rcs);
        enterprise.setLogo(logo);
        repository.save(enterprise);
    }
}
