package com.cintulova.TaskInsuranceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "beginning_of_insurance", nullable = false)
    private LocalDate beginningOfInsurance;

    @Column(name = "insured_amount", nullable = false)
    private Long insuredAmount;

    @Column(name = "date_of_signing_mortgage", nullable = false)
    private LocalDate dateOfSigningMortgage;

    @JsonIgnore
    @OneToMany(mappedBy = "quotation")
    private Set<Subscription> subscriptions;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
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
