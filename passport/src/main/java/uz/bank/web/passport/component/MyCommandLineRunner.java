package uz.bank.web.passport.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.bank.web.passport.model.Passport;
import uz.bank.web.passport.model.Role;
import uz.bank.web.passport.model.User;
import uz.bank.web.passport.model.others.RoleName;
import uz.bank.web.passport.repository.PassportRepository;
import uz.bank.web.passport.repository.RoleRepository;
import uz.bank.web.passport.repository.UserRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PassportRepository passportRepository;
    @Override
    public void run(String... args) throws Exception {



        Role admin= Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        roleRepository.save(admin);
        if (!userRepository.existsByUsername("admin")){
            User user=User.builder()
                    .username("admin")
                    .email("a")
                    .active(true)
                    .password(passwordEncoder.encode("admin"))
                    .roles(Collections.singleton(admin))
                    .build();
            System.out.println(userRepository.save(user));
        }



        // Client for service 2
        Role apiClient= Role.builder()
                .roleName(RoleName.ROLE_API_CLIENT)
                .build();
        roleRepository.save(apiClient);
        if (!userRepository.existsByUsername("apiClient")){
            User user=User.builder()
                    .username("apiClient")
                    .email("apiClient")
                    .active(true)
                    .password(passwordEncoder.encode("apiClient"))
                    .roles(Collections.singleton(apiClient))
                    .build();
            System.out.println(userRepository.save(user));
        }



        passportCreator("Anvar",
                "Turopov",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "1"
                );
        passportCreator("Bobur",
                "Abdullayev",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "2"
        );

        passportCreator("Ali",
                "Parmonov",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "3"
        );

        passportCreator("Vali",
                "Parmonov",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "4"
        );

        passportCreator("Sodiq",
                "Karimov",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "5"
        );

        passportCreator("Gani",
                "Teshayev",
                "Toshkent",
                "19991123",
                "20150309",
                "20250309",
                "UZB",
                "a",
                "6"
        );

    }



    public Date convert(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = format.parse(date);
        return new Date(parsed.getTime());
    }

    public void passportCreator(String name,
                                String surname,
                                String place_of_birth,
                                String date_of_birth,
                                String date_of_issue,
                                String date_of_expiry,
                                String country_code,
                                String series,
                                String number


    ) throws ParseException {
        if (!passportRepository.existsBySeriesAndNumber(series,number))
        passportRepository.save(Passport
                .builder()
                .country_code(country_code)
                .date_of_birth(convert(date_of_birth))
                .date_of_issue(convert(date_of_issue))
                .date_of_expiry(convert(date_of_expiry))
                .name(name)
                .surname(surname)
                .place_of_birth(place_of_birth)
                .series(series)
                .number(number)
                .build());
    }
}
