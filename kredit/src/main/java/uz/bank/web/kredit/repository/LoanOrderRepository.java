package uz.bank.web.kredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.kredit.model.LoanOrder;

import java.util.UUID;

public interface LoanOrderRepository extends JpaRepository<LoanOrder, UUID> {
}
