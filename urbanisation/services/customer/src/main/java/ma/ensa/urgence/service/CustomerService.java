package ma.ensa.urgence.service;

import lombok.RequiredArgsConstructor;
import ma.ensa.urgence.model.Customer;
import ma.ensa.urgence.repository.CustomerRepository;
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
