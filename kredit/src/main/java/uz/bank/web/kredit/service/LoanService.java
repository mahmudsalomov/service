package uz.bank.web.kredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.web.kredit.dto.LoanRequestDto;
import uz.bank.web.kredit.model.Customer;
import uz.bank.web.kredit.model.Loan;
import uz.bank.web.kredit.model.LoanOrder;
import uz.bank.web.kredit.payload.ApiResponse;
import uz.bank.web.kredit.repository.CustomerRepository;
import uz.bank.web.kredit.repository.LoanOrderRepository;
import uz.bank.web.kredit.repository.LoanRepository;
import uz.bank.web.kredit.utils.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    private Converter converter;

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanOrderRepository loanOrderRepository;

    // Per year
    public double creditCalculator(double loanAmount, Loan loan){
        double x = ((loanAmount * loan.getAnnual_loan_interest()) / (100 * loan.getDuration()))
                + loanAmount / loan.getDuration();
        return x;
    }


    public double creditCalculatorDesc(double finalAmount, Loan loan){
        double x = finalAmount*loan.getDuration()/(1+loan.getAnnual_loan_interest()/100);
        return x;
    }

    public double income(double salary){
        return 12*(salary-salary*0.3);
    }


    public boolean check(double salary, double loanAmount, Loan loan){

        return !(creditCalculator(loanAmount,
                loan) > income(salary));
    }

    public Double findRecommendSumma(double salary, Loan loan){
        double b = income(salary);
        if (b>= creditCalculator(loan.getStartingAmount(),loan)){
            return creditCalculatorDesc(b,loan);
        } else return null;
    }


    public ApiResponse loan(LoanRequestDto loanRequestDto, Customer customer){
        try {
            Optional<Loan> loan = loanRepository.findById(loanRequestDto.getLoan_id());
            if (!loan.isPresent()) return converter.apiError("Kredit turi tog'ri tanlanmagan!",loan);
            if (loan.get().getEndingAmount()<loanRequestDto.getSumma()) return converter.apiError("Kredit summasi maksimaldan oshib ketgan!",loan);
            if (loan.get().getStartingAmount() > loanRequestDto.getSumma()) return converter.apiError("Kredit summasi minimaldan kam!",loan);

            if (check(loanRequestDto.getSalary(), loanRequestDto.getSumma(),loan.get())){

                LoanOrder loanOrder=LoanOrder
                        .builder()
                        .active(true)
                        .customer(customer)
                        .finalLoanAmount(creditCalculator(loanRequestDto.getSumma(),loan.get())*loan.get().getDuration())
                        .salary(loanRequestDto.getSalary())
                        .duration(loan.get().getDuration())
                        .loan(loan.get())
                        .loanAmount(loanRequestDto.getSumma())
                        .build();
                LoanOrder order = loanOrderRepository.save(loanOrder);
                return converter.apiSuccess("Arizangiz qabul qilindi!",converter.orderToDto(order));

            }
            else {
                Double recommendSumma = findRecommendSumma(loanRequestDto.getSalary(), loan.get());
                if (recommendSumma!=null) return converter.apiSuccess("Kiritilgan kredit miqdoriga daromadingiz yetmaydi. Sizga tavsiya qilinadigan kredit miqdori: " +(Math.round(recommendSumma)), loan);
                else return converter.apiError("Afsuski bu kredit sizning daromadingizga mos emas.",loan);
            }

        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError("Server error");
        }

    }


    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(loanRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
