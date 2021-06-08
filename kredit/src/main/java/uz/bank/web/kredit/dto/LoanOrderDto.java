package uz.bank.web.kredit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanOrderDto {
    private UUID loan_code;
    private String fullName;
    private double amount;
    private double full_amount;
    private int duration;
    private String passport_series;
    private String passport_number;
}
