package com.bank.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "SSN ID is required")
    @Pattern(regexp = "^SSN-\\d{4}$", message = "SSN ID must be in format SSN-XXXX")
    private String ssnId;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be a valid 10-digit Indian mobile number")
    private String phoneNumber;
    
    @NotBlank(message = "Aadhar number is required")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}$", message = "Aadhar must be in format XXXX-XXXX-XXXX")
    private String aadharNo;
    
    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "^[A-Z]{5}\\d{4}[A-Z]$", message = "PAN must be in format ABCDE1234F")
    private String panNo;
    
    @NotNull(message = "Account balance is required")
    @Min(value = 0, message = "Account balance cannot be negative")
    private Double accountBalance = 0.0;

    // Default Constructor
    public Customer() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSsnId() { return ssnId; }
    public void setSsnId(String ssnId) { this.ssnId = ssnId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAadharNo() { return aadharNo; }
    public void setAadharNo(String aadharNo) { this.aadharNo = aadharNo; }

    public String getPanNo() { return panNo; }
    public void setPanNo(String panNo) { this.panNo = panNo; }

    public Double getAccountBalance() { return accountBalance; }
    public void setAccountBalance(Double accountBalance) { this.accountBalance = accountBalance; }
}