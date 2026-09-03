package com.bit.mango.salesmarketing.customer;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The "Service" layer is where your BUSINESS LOGIC lives - anything
 * more than a plain "get this row from the database".
 *
 * Rule of thumb for later:
 *   Controller = handles the web request/response (talks to React)
 *   Service    = decides WHAT should happen (the actual logic)
 *   Repository = talks to the database
 *
 * Right now this Service is simple (it just forwards to the
 * Repository), but this is exactly where you'd add things like
 * "before saving, auto-generate the customer_code" - see below.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Spring automatically "injects" the repository here - you never
    // write `new CustomerRepository()` yourself, Spring hands you a
    // ready-to-use one. This pattern is called Dependency Injection,
    // you'll see it everywhere in Spring code.
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public List<Customer> getCustomersByStatus(String status) {
        return customerRepository.findByStatus(status);
    }

    public Customer createCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedData) {
        Customer existing = getCustomerById(id);
        existing.setCompanyName(updatedData.getCompanyName());
        existing.setCustomerType(updatedData.getCustomerType());
        existing.setCountry(updatedData.getCountry());
        existing.setRegion(updatedData.getRegion());
        existing.setCity(updatedData.getCity());
        existing.setStatus(updatedData.getStatus());
        existing.setPaymentTerms(updatedData.getPaymentTerms());
        existing.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(existing);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
