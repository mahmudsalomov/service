package uz.bank.web.kredit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.kredit.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Short> {
}
