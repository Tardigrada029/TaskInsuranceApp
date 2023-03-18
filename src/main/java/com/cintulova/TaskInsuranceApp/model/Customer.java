package com.cintulova.TaskInsuranceApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tbl_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Size(min = 2, max = 150)
    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Size(min = 2, max = 150)
    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;

    @Size(min = 2, max = 150)
    @Column(name = "middle_name", length = 150)
    private String middleName;

    @Size(min = 10, max = 150)
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Size(min = 7, max = 14)
    @Column(name = "phone_number", nullable = false, length = 14)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

}
