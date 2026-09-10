package com.bank.customerservice.controller;

import com.bank.customerservice.model.Customer;
import com.bank.customerservice.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:4200") 
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer account not found for ID: " + id));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedData) {
        return repository.findById(id).map(customer -> {
            customer.setSsnId(updatedData.getSsnId());
            customer.setFirstName(updatedData.getFirstName());
            customer.setLastName(updatedData.getLastName());
            customer.setEmail(updatedData.getEmail());
            customer.setPhoneNumber(updatedData.getPhoneNumber());
            customer.setAadharNo(updatedData.getAadharNo());
            customer.setPanNo(updatedData.getPanNo());
            // Balance is NOT updated here - use /transaction endpoint instead
            return repository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer account not found for ID: " + id));
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer account not found for ID: " + id);
        }
        repository.deleteById(id);
        return "Account successfully removed.";
    }
}