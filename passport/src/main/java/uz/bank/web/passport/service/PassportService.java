package uz.bank.web.passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.web.passport.model.Passport;
import uz.bank.web.passport.repository.PassportRepository;

import java.util.Optional;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public Passport info(String series, String number) {
        try {
            Optional<Passport> passport = passportRepository.findBySeriesAndNumber(series, number);
            return passport.orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
