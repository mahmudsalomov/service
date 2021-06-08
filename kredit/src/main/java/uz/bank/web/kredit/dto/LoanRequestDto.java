package uz.bank.web.kredit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bank.web.kredit.model.Loan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequestDto {
    private String seriya;
    private String number;
    private double salary;
    private double summa;
    private Short loan_id;
}
