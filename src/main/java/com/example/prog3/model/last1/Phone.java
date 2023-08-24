package com.example.prog3.model.last1;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phoneEmployee", referencedColumnName = "id")
    private Employee phoneEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phoneEnterprise", referencedColumnName = "id")
    private Enterprise phoneEnterprise;

}
