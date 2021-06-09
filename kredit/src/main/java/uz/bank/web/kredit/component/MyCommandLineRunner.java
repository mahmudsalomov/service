package uz.bank.web.kredit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.bank.web.kredit.model.Customer;
import uz.bank.web.kredit.model.Loan;
import uz.bank.web.kredit.model.Passport;
import uz.bank.web.kredit.model.Role;
import uz.bank.web.kredit.model.others.CreditType;
import uz.bank.web.kredit.model.others.RoleName;
import uz.bank.web.kredit.repository.CustomerRepository;
import uz.bank.web.kredit.repository.LoanRepository;
import uz.bank.web.kredit.repository.PassportRepository;
import uz.bank.web.kredit.repository.RoleRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Override
    public void run(String... args) throws Exception {



        Role admin= Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        roleRepository.save(admin);


        Role customer= Role.builder()
                .roleName(RoleName.ROLE_CUSTOMER)
                .build();
        roleRepository.save(customer);

        customerCreator("admin","admin","admin",admin);


        customerCreator("anvar","anvar","anvar",customer);
        customerCreator("sodiq","sodiq","sodiq",customer);
        customerCreator("vali","vali","vali",customer);
        customerCreator("ali","ali","ali",customer);
        customerCreator("gani","gani","gani",customer);
        customerCreator("bobur","bobur","bobur",customer);

        Loan ipoteka=Loan
                .builder()
                .name("Ipoteka")
                .type(CreditType.IPOTECA)
                .startingAmount(100000000.0)
                .endingAmount(3000000000.0)
                .duration(10)
                .annual_loan_interest(30)
                .build();

        loanRepository.save(ipoteka);

        Loan education=Loan
                .builder()
                .name("Ta'lim")
                .type(CreditType.EDUCATION)
                .startingAmount(15000000.0)
                .endingAmount(180000000.0)
                .duration(5)
                .annual_loan_interest(18)
                .build();
        loanRepository.save(education);



    }


    public void customerCreator(String username, String password, String email,Role role){
        if (!customerRepository.existsByUsername(username)){
            Customer user=Customer.builder()
                    .username(username)
                    .email(email)
                    .active(true)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singleton(role))
                    .build();
            System.out.println(customerRepository.save(user));
        }
    }

}
