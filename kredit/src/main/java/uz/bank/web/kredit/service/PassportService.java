package uz.bank.web.kredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.web.kredit.model.Passport;
import uz.bank.web.kredit.payload.ApiResponse;
import uz.bank.web.kredit.repository.PassportRepository;
import uz.bank.web.kredit.utils.Converter;

import java.util.List;

@Service
public class PassportService {
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private Converter converter;

    public ApiResponse getAll(){
        try {
            List<Passport> all = passportRepository.findAll();
            return converter.apiSuccess("All passports in service 2",all);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
