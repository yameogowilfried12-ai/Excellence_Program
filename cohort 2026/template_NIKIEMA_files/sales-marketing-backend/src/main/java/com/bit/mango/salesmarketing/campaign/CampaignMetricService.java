package com.bit.mango.salesmarketing.campaign;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignMetricService {

    private final CampaignMetricRepository repository;

    public CampaignMetricService(CampaignMetricRepository repository) {
        this.repository = repository;
    }

    public List<CampaignMetric> getAll() {
        return repository.findAll();
    }

    public CampaignMetric getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignMetric not found with id: " + id));
    }

    public CampaignMetric create(CampaignMetric item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public CampaignMetric update(Integer id, CampaignMetric updatedData) {
        CampaignMetric existing = getById(id);
        existing.setCampaignId(updatedData.getCampaignId());
        existing.setMetricDate(updatedData.getMetricDate());
        existing.setClicks(updatedData.getClicks());
        existing.setSignups(updatedData.getSignups());
        existing.setReach(updatedData.getReach());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
