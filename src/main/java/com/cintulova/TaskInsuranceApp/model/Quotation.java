package com.cintulova.TaskInsuranceApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tbl_quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany
    @JoinColumn(name = "quotation_id")
    private Set<Subscription> subscriptions = new HashSet<>();

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
