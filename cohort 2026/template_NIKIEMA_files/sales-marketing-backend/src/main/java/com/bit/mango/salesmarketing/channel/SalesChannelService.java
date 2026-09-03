package com.bit.mango.salesmarketing.channel;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalesChannelService {

    private final SalesChannelRepository repository;

    public SalesChannelService(SalesChannelRepository repository) {
        this.repository = repository;
    }

    public List<SalesChannel> getAll() {
        return repository.findAll();
    }

    public SalesChannel getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SalesChannel not found with id: " + id));
    }

    public SalesChannel create(SalesChannel item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public SalesChannel update(Integer id, SalesChannel updatedData) {
        SalesChannel existing = getById(id);
        existing.setName(updatedData.getName());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
