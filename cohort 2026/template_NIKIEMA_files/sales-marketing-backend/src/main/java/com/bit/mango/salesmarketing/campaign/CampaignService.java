package com.bit.mango.salesmarketing.campaign;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public List<Campaign> getAll() {
        return repository.findAll();
    }

    public Campaign getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
    }

    public Campaign create(Campaign item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public Campaign update(Integer id, Campaign updatedData) {
        Campaign existing = getById(id);
        existing.setName(updatedData.getName());
        existing.setStatus(updatedData.getStatus());
        existing.setBudgetEur(updatedData.getBudgetEur());
        existing.setBudgetSpentEur(updatedData.getBudgetSpentEur());
        existing.setEstimatedReach(updatedData.getEstimatedReach());
        existing.setCurrency(updatedData.getCurrency());
        existing.setStartDate(updatedData.getStartDate());
        existing.setEndDate(updatedData.getEndDate());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
