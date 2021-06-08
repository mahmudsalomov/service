package uz.bank.web.kredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.kredit.model.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByUsername(String s);

    boolean existsByUsername(String username);
}
