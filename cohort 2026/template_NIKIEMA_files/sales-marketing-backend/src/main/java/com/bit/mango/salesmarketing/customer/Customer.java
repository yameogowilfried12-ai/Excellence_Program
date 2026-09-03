package com.bit.mango.salesmarketing.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * An "Entity" is a Java class that represents ONE ROW of ONE TABLE
 * in your database. This class = the "customers" table you already
 * created in PostgreSQL. Every field below = one column.
 *
 * Spring/Hibernate reads these annotations (@Table, @Column, etc.)
 * to know how to translate between "a Customer object in Java" and
 * "a row in the customers table" automatically - you never write
 * raw SQL for basic operations (create, read, update, delete).
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id // marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // matches your SERIAL column
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_code", unique = true)
    private String customerCode;

    @NotBlank(message = "Company name is required")
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "customer_type", nullable = false)
    private String customerType; // distributor / retailer / direct_export / other

    private String country;
    private String region;
    private String city;

    @Column(nullable = false)
    private String status = "prospect"; // active / inactive / prospect

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ---- Getters and setters ----
    // Java needs these so other classes (like the ones that turn this
    // object into JSON for React) can read/write each field safely.
    // Boring but necessary - your IDE (IntelliJ/VS Code) can generate
    // these for you automatically, you don't have to type them by hand.

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
