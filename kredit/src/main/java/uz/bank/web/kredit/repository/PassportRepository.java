package uz.bank.web.kredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bank.web.kredit.model.Passport;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {
    boolean existsBySeriesAndNumber(@NotNull String series, @NotNull String number);
    Passport findBySeriesAndNumber(@NotNull String series, @NotNull String number);

}
