package ma.ensa.ecommerce.service;

import lombok.RequiredArgsConstructor;
import ma.ensa.ecommerce.model.Customer;
import ma.ensa.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final  CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // find by id
    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

}
