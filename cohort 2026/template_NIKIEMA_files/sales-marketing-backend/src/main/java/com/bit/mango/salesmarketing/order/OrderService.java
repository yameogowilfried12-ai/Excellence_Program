package com.bit.mango.salesmarketing.order;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order create(Order item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public Order update(Integer id, Order updatedData) {
        Order existing = getById(id);
        existing.setCustomerId(updatedData.getCustomerId());
        existing.setSalesChannelId(updatedData.getSalesChannelId());
        existing.setOrderDate(updatedData.getOrderDate());
        existing.setTotalVolumeKg(updatedData.getTotalVolumeKg());
        existing.setTotalValueEur(updatedData.getTotalValueEur());
        existing.setStatus(updatedData.getStatus());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
