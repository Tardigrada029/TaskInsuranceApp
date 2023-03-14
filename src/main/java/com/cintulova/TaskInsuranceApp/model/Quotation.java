package com.cintulova.TaskInsuranceApp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tbl_quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quotation_id")
    private Long id;

    @Column(name = "beginning_of_insurance", nullable = false)
    private LocalDate beginningOfInsurance;

    @Column(name = "insured_amount", nullable = false)
    private Long insuredAmount;

    @Column(name = "date_of_signing_mortgage", nullable = false)
    private LocalDate dateOfSigningMortgage;

    @ManyToOne
    private Customer customer;

}
