package uz.bank.web.kredit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.kredit.model.Role;

public interface RoleRepository extends JpaRepository<Role,Short> {
}
