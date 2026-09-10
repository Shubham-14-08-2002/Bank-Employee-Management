package com.bank.employee_service.controller;

import com.bank.employee_service.model.Employee;
import com.bank.employee_service.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee record not found for ID: " + id));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee updatedData) {
        return repository.findById(id).map(employee -> {
            employee.setEmployeeId(updatedData.getEmployeeId());
            employee.setFirstName(updatedData.getFirstName());
            employee.setLastName(updatedData.getLastName());
            employee.setEmail(updatedData.getEmail());
            employee.setPhoneNumber(updatedData.getPhoneNumber());
            employee.setDepartment(updatedData.getDepartment());
            employee.setAddress(updatedData.getAddress());
            return repository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee record not found for ID: " + id));
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee record not found for ID: " + id);
        }
        repository.deleteById(id);
        return "Employee record successfully removed.";
    }
}