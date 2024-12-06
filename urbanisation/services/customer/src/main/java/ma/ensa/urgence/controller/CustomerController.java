package ma.ensa.urgence.controller;

import lombok.RequiredArgsConstructor;
import ma.ensa.urgence.model.Customer;
import ma.ensa.urgence.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/test")
    public String test() {
        return "TEST cUSTOMER";
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    // find by id
    @GetMapping("/{id}")
    public Customer get(@PathVariable int id) {
        return customerService.findById(id);
    }


}
