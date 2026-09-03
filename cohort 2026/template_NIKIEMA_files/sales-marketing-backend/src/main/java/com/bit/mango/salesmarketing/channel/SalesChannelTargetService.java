package com.bit.mango.salesmarketing.channel;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalesChannelTargetService {

    private final SalesChannelTargetRepository repository;

    public SalesChannelTargetService(SalesChannelTargetRepository repository) {
        this.repository = repository;
    }

    public List<SalesChannelTarget> getAll() {
        return repository.findAll();
    }

    public SalesChannelTarget getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SalesChannelTarget not found with id: " + id));
    }

    public SalesChannelTarget create(SalesChannelTarget item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public SalesChannelTarget update(Integer id, SalesChannelTarget updatedData) {
        SalesChannelTarget existing = getById(id);
        existing.setChannelId(updatedData.getChannelId());
        existing.setPeriodStart(updatedData.getPeriodStart());
        existing.setPeriodEnd(updatedData.getPeriodEnd());
        existing.setRevenueTargetEur(updatedData.getRevenueTargetEur());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
