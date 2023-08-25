package com.example.prog3.model.last1;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String slogan;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nif;

    @Column(nullable = false)
    private String stat;

    @Column(nullable = false)
    private String rcs;

    @Lob
    private String logo;

    @OneToMany(mappedBy = "phoneEnterprise")
    private List<Phone> phones;
}
