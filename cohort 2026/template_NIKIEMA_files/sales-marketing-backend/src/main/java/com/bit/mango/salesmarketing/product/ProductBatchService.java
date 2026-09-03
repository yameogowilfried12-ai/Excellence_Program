package com.bit.mango.salesmarketing.product;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductBatchService {

    private final ProductBatchRepository repository;

    public ProductBatchService(ProductBatchRepository repository) {
        this.repository = repository;
    }

    public List<ProductBatch> getAll() {
        return repository.findAll();
    }

    public ProductBatch getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBatch not found with id: " + id));
    }

    public ProductBatch create(ProductBatch item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public ProductBatch update(Integer id, ProductBatch updatedData) {
        ProductBatch existing = getById(id);
        existing.setBatchCode(updatedData.getBatchCode());
        existing.setProductId(updatedData.getProductId());
        existing.setStockT(updatedData.getStockT());
        existing.setStatus(updatedData.getStatus());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
