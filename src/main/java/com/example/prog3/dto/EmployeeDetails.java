package com.example.prog3.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeDetails {
    private Long id;
    private String matricule;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate employementDate;
    private LocalDate departureDate;
    private String cnaps;
    private int salary;
    private String image;
}
