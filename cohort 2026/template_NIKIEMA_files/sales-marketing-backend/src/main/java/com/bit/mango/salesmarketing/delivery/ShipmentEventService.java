package com.bit.mango.salesmarketing.delivery;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentEventService {

    private final ShipmentEventRepository repository;

    public ShipmentEventService(ShipmentEventRepository repository) {
        this.repository = repository;
    }

    public List<ShipmentEvent> getAll() {
        return repository.findAll();
    }

    public ShipmentEvent getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShipmentEvent not found with id: " + id));
    }

    public ShipmentEvent create(ShipmentEvent item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public ShipmentEvent update(Integer id, ShipmentEvent updatedData) {
        ShipmentEvent existing = getById(id);
        existing.setShipmentId(updatedData.getShipmentId());
        existing.setStep(updatedData.getStep());
        existing.setEventTime(updatedData.getEventTime());
        existing.setNote(updatedData.getNote());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
