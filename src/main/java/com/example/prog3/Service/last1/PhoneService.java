package com.example.prog3.Service.last1;

import com.example.prog3.Repository.last1.PhoneRepository;
import com.example.prog3.model.last1.Employee;
import com.example.prog3.model.last1.Enterprise;
import com.example.prog3.model.last1.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PhoneService {
    private final PhoneRepository repository;
    public Phone getByEmployee(Employee employee){return repository.findByPhoneEmployee(employee);}

    public List<Phone> getAllByEmployee(Employee employee){return repository.findPhonesByPhoneEmployee(employee);}

    public void createPhoneNumberEmployee(String codeCountry, String phoneNumbers, Employee employee){
        String[] numberList = phoneNumbers.split(",");
        List<Phone> phoneList = getAllByEmployee(employee);
        for (String phoneNumber : numberList) {
            Phone phone = new Phone();
            phone.setPhoneEmployee(employee);
            phone.setPhoneNumber(phoneNumber);
            phone.setCountryCode(codeCountry);
            if(!phoneList.contains(phone)){
            repository.save(phone);
            } else {
                throw new IllegalArgumentException("Phone number already registered");
            }
        }
    }
    public void createPhoneNumberEnterprise(String codeCountry,String phoneNumbers, Enterprise enterprise){
        String[] numberList = phoneNumbers.split(",");
        for (String phoneNumber : numberList) {
            if(phoneNumber.length() == 10){
                Phone phone = new Phone();
                phone.setPhoneEnterprise(enterprise);
                phone.setPhoneNumber(phoneNumber);
                phone.setCountryCode(codeCountry);
                repository.save(phone);
            }else{
                throw new IllegalArgumentException("Phone number needs to be exactly 10");
            }
        }
    }
    public void updatePhoneNumber(String codeCountry,String phoneNumbers,Employee employee){
        if(phoneNumbers == "" || phoneNumbers == null){
            //do nothing
        }else {
            String[] numberList = phoneNumbers.split(",");
            List<Phone> phoneList = getAllByEmployee(employee);
            for (String phoneNumber : numberList) {
                    Phone phone = new Phone();
                    phone.setPhoneEmployee(employee);
                    phone.setPhoneNumber(phoneNumber);
                    phone.setCountryCode(codeCountry);
                    if(!phoneList.contains(phone)){
                        repository.save(phone);
                    } else {
                        throw new IllegalArgumentException("Phone number already registered");
                    }
            }
        }
    }
    public void updatePhoneNumberEnterprise(String codeCountry,String phoneNumbers,Enterprise enterprise){
        if(phoneNumbers == "" || phoneNumbers == null){
            //do nothing
        }else {
            String[] numberList = phoneNumbers.split(",");
            List<Phone> enterprisePhone = repository.findPhonesByPhoneEnterprise(enterprise);
            for (String phoneNumber : numberList) {
                Phone phone = new Phone();
                phone.setPhoneEnterprise(enterprise);
                phone.setPhoneNumber(phoneNumber);
                phone.setCountryCode(codeCountry);
                    if(!enterprisePhone.contains(phone)){
                        repository.save(phone);
                    }else
                    {throw new IllegalArgumentException("Phone number already registred");}
                }
        }
    }
}
