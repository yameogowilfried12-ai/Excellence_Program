package com.bit.mango.salesmarketing.pricing;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PricingHistoryService {

    private final PricingHistoryRepository repository;

    public PricingHistoryService(PricingHistoryRepository repository) {
        this.repository = repository;
    }

    public List<PricingHistory> getAll() {
        return repository.findAll();
    }

    public PricingHistory getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PricingHistory not found with id: " + id));
    }

    public PricingHistory create(PricingHistory item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public PricingHistory update(Integer id, PricingHistory updatedData) {
        PricingHistory existing = getById(id);
        existing.setProductId(updatedData.getProductId());
        existing.setPriceDate(updatedData.getPriceDate());
        existing.setPriceEurPerKg(updatedData.getPriceEurPerKg());
        existing.setHarvestSeasonFactor(updatedData.getHarvestSeasonFactor());
        existing.setSeasonLabel(updatedData.getSeasonLabel());
        existing.setStockLevelTons(updatedData.getStockLevelTons());
        existing.setStockTargetTons(updatedData.getStockTargetTons());
        existing.setDemandFactor(updatedData.getDemandFactor());
        existing.setDemandIndexLabel(updatedData.getDemandIndexLabel());
        existing.setIsApplied(updatedData.getIsApplied());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
