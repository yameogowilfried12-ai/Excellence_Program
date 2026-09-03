package com.bit.mango.salesmarketing.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The "Controller" is the ONLY layer that React actually talks to.
 * Each method below becomes a URL your frontend can call.
 *
 * @RequestMapping("/api/customers") means every URL in this file
 * starts with that prefix. So the methods below become:
 *
 *   GET    /api/customers            -> list all customers
 *   GET    /api/customers/5          -> get customer #5
 *   GET    /api/customers?status=active  -> filter by status
 *   POST   /api/customers            -> create a new customer
 *   PUT    /api/customers/5          -> update customer #5
 *   DELETE /api/customers/5          -> delete customer #5
 *
 * @CrossOrigin allows your React app (running on a different port,
 * e.g. localhost:3000) to actually call this API - without it, the
 * browser blocks the request for security reasons (CORS).
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(@RequestParam(required = false) String status) {
        if (status != null) {
            return customerService.getCustomersByStatus(status);
        }
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}
