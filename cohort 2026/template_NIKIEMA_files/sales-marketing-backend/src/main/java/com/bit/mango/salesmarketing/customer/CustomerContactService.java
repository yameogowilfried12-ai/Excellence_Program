package com.bit.mango.salesmarketing.customer;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerContactService {

    private final CustomerContactRepository repository;

    public CustomerContactService(CustomerContactRepository repository) {
        this.repository = repository;
    }

    public List<CustomerContact> getAll() {
        return repository.findAll();
    }

    public CustomerContact getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerContact not found with id: " + id));
    }

    public CustomerContact create(CustomerContact item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public CustomerContact update(Integer id, CustomerContact updatedData) {
        CustomerContact existing = getById(id);
        existing.setCustomerId(updatedData.getCustomerId());
        existing.setFullName(updatedData.getFullName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());
        existing.setRole(updatedData.getRole());
        existing.setIsPrimary(updatedData.getIsPrimary());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
