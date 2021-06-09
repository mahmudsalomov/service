package uz.bank.web.kredit.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bank.web.kredit.service.PassportService;

@RestController
@RequestMapping("api/passport")
public class PassportController {

    @Autowired
    private PassportService passportService;

    @GetMapping("/all")
    public HttpEntity<?> all(){
        return ResponseEntity.ok(passportService.getAll());
    }
}
