package com.bank.employee_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {

    // Maps directly to the GET mapping we verified inside CustomerController
    @GetMapping("/api/v1/customers")
    List<Object> getAllCustomers(); 
}