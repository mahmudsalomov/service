package uz.bank.web.kredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.bank.web.kredit.dto.LoanRequestDto;
import uz.bank.web.kredit.model.Auth;
import uz.bank.web.kredit.model.Customer;
import uz.bank.web.kredit.model.Passport;
import uz.bank.web.kredit.payload.ApiResponse;
import uz.bank.web.kredit.repository.CustomerRepository;
import uz.bank.web.kredit.repository.PassportRepository;
import uz.bank.web.kredit.utils.Converter;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private Auth auth;
    @Autowired
    private Converter converter;
    @Autowired
    private LoanService loanService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PassportRepository passportRepository;

    @Value("${passport.service.url}")
    private String url;


    /** Web service **/
    public ApiResponse credit(LoanRequestDto loanRequestDto, Customer customer){
        try {

            customer=customerRepository.findByUsername(customer.getUsername());
//            System.out.println(customer);
                if (customer.getPassport()==null){


                    if (passportRepository.existsBySeriesAndNumber(loanRequestDto.getSeriya(),loanRequestDto.getNumber())){

                        return converter.apiError("Ushbu passport boshqa foydalanuvchiga tegishli!");
                    }

                    RestTemplate restTemplate=new RestTemplate();


                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                            .queryParam("series", loanRequestDto.getSeriya())
                            .queryParam("number", loanRequestDto.getNumber());



                    HttpEntity<?> entity = new HttpEntity<>(converter.createHeaders(auth.getUsername(),auth.getPassword()));


                    HttpEntity<Passport> passport = restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        entity,
                        Passport.class);


                    System.out.println(passport.getBody());
                    if (passport.getBody()==null) return converter.apiError("Passport topilmadi!");
                    passport.getBody().setId(null);
                    passportRepository.save(passport.getBody());
                    customer.setPassport(passport.getBody());
                    customerRepository.save(customer);
                }
                return loanService.loan(loanRequestDto,customer);

        }
        catch (Exception e){
            e.printStackTrace();
            return converter.apiError("Serverda xatolik");
        }
    }


    public ApiResponse getAll() {
        try {
            List<Customer> all = customerRepository.findAll();
            return converter.apiSuccess(all);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
