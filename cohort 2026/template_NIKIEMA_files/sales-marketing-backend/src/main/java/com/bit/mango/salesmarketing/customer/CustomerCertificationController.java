package com.bit.mango.salesmarketing.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * For a junction table, a full CRUD API doesn't really make sense
 * (there's nothing to "update" - a link either exists or it doesn't).
 * Instead we expose simple "link" / "unlink" / "list" actions.
 *
 *   GET    /api/customers/{customerId}/certifications        -> list them
 *   POST   /api/customers/{customerId}/certifications/{certId} -> link one
 *   DELETE /api/customers/{customerId}/certifications/{certId} -> unlink one
 */
@RestController
@RequestMapping("/api/customers/{customerId}/certifications")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerCertificationController {

    private final CustomerCertificationRepository repository;

    public CustomerCertificationController(CustomerCertificationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CustomerCertification> getForCustomer(@PathVariable Integer customerId) {
        return repository.findByCustomerId(customerId);
    }

    @PostMapping("/{certificationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCertification link(@PathVariable Integer customerId, @PathVariable Integer certificationId) {
        return repository.save(new CustomerCertification(customerId, certificationId));
    }

    @DeleteMapping("/{certificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlink(@PathVariable Integer customerId, @PathVariable Integer certificationId) {
        repository.deleteById(new CustomerCertificationId(customerId, certificationId));
    }
}
