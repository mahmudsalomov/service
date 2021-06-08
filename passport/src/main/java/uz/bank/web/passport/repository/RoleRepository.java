package uz.bank.web.passport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.passport.model.Role;

public interface RoleRepository extends JpaRepository<Role,Short> {
}
