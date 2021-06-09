package uz.bank.web.passport.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.web.passport.service.PassportService;

@RestController
@RequestMapping("api/passport")
@CrossOrigin
public class PassportController {
    @Autowired
    private PassportService passportService;


    // Passport info using passport series and number
    @GetMapping("/info")
    public HttpEntity<?> info(@RequestParam String series, @RequestParam String number){
        return ResponseEntity.ok(passportService.info(series,number));
    }


}
