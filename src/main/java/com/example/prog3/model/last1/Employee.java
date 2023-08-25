package com.example.prog3.model.last1;

import com.example.prog3.model.last2.Cnaps;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Csp csp;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(unique = true,nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,unique = true)
    private String emailPro;

    @Column(nullable = false,unique = true)
    private String emailPerso;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Integer child;

    @Column(nullable = false)
    private LocalDate employementDate;

    private LocalDate departureDate;

    @Column(nullable = false)
    private String cnaps;

    @Column(nullable = false)
    private String cin;

    @Column(nullable = false)
    private int wage;

    @Lob
    private byte[] emplImg;

    @OneToMany(mappedBy = "phoneEmployee")
    private List<Phone> phones;

    public enum Sex{
        M,F
    }

    public enum Csp{
        M1,M2,OS1,OS2,OS3,OP1
    }
}
