package uz.bank.web.kredit.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.web.kredit.component.CurrentCustomer;
import uz.bank.web.kredit.dto.LoanRequestDto;
import uz.bank.web.kredit.model.Customer;
import uz.bank.web.kredit.service.CustomerService;
import uz.bank.web.kredit.service.LoanService;

@RestController
@RequestMapping("api/credit")
@CrossOrigin
public class LoanController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoanService loanService;

    @PostMapping("/order")
    public HttpEntity<?> credit(@RequestBody LoanRequestDto loanRequestDto, @CurrentCustomer Customer customer){

        return ResponseEntity.ok(customerService.credit(loanRequestDto,customer));
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(loanService.getAll());
    }




}
