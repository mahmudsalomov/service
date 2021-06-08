package uz.bank.web.kredit.model;

import lombok.*;
import uz.bank.web.kredit.model.template.EntityUUID;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LoanOrder extends EntityUUID {


    private double salary;

    private double loanAmount;

    private double finalLoanAmount;

    private int duration;

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Customer customer;

    private boolean active;

}
