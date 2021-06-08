package uz.bank.web.kredit.model;

import lombok.*;
import uz.bank.web.kredit.model.others.CreditType;
import uz.bank.web.kredit.model.others.RoleName;
import uz.bank.web.kredit.model.template.EntityShort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Loan extends EntityShort {

    @NotNull
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CreditType type;

    @NotNull
    private double startingAmount;
    @NotNull
    private double endingAmount;

    // 0-100
    @NotNull
    private double annual_loan_interest;

    // Only years
    private int duration;


}
