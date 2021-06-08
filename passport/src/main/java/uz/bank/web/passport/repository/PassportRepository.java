package uz.bank.web.passport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.passport.model.Passport;

import java.util.Optional;
import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {
    Optional<Passport> findBySeriesAndNumber(String series, String number);
    boolean existsBySeriesAndNumber(String series, String number);
}
