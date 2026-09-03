package com.bit.mango.salesmarketing.pricing;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PricingRecommendationService {

    private final PricingRecommendationRepository repository;

    public PricingRecommendationService(PricingRecommendationRepository repository) {
        this.repository = repository;
    }

    public List<PricingRecommendation> getAll() {
        return repository.findAll();
    }

    public PricingRecommendation getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingRecommendation not found with id: " + id));
    }

    public PricingRecommendation create(PricingRecommendation item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public PricingRecommendation update(Integer id, PricingRecommendation updatedData) {
        PricingRecommendation existing = getById(id);
        existing.setProductId(updatedData.getProductId());
        existing.setMessage(updatedData.getMessage());
        existing.setSuggestedPriceEur(updatedData.getSuggestedPriceEur());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
