package com.cintulova.TaskInsuranceApp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "beginning_of_insurance", nullable = false)
    private LocalDate beginningOfInsurance;

    @Column(name = "insured_amount", nullable = false)
    private Long insuredAmount;

    @Column(name = "date_of_signing_mortgage", nullable = false)
    private LocalDate dateOfSigningMortgage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // constructor
    public Quotation(Long id, LocalDate beginningOfInsurance, Long insuredAmount,
                     LocalDate dateOfSigningMortgage, Customer customer) {
        this.id = id;
        this.beginningOfInsurance = beginningOfInsurance;
        this.insuredAmount = insuredAmount;
        this.dateOfSigningMortgage = dateOfSigningMortgage;
        this.customer = customer;
    }

}
