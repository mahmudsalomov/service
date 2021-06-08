package uz.bank.web.passport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.passport.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String s);
    boolean existsByUsername(String username);
}
