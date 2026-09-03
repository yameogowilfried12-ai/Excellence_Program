package com.bit.mango.salesmarketing.delivery;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository repository;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    public List<Shipment> getAll() {
        return repository.findAll();
    }

    public Shipment getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));
    }

    public Shipment create(Shipment item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public Shipment update(Integer id, Shipment updatedData) {
        Shipment existing = getById(id);
        existing.setShipmentCode(updatedData.getShipmentCode());
        existing.setOrderId(updatedData.getOrderId());
        existing.setOrigin(updatedData.getOrigin());
        existing.setDestination(updatedData.getDestination());
        existing.setStatus(updatedData.getStatus());
        existing.setEta(updatedData.getEta());
        existing.setVesselName(updatedData.getVesselName());
        existing.setVoyageNumber(updatedData.getVoyageNumber());
        existing.setDelayReason(updatedData.getDelayReason());
        existing.setCurrentLat(updatedData.getCurrentLat());
        existing.setCurrentLon(updatedData.getCurrentLon());
        existing.setSpeedKnots(updatedData.getSpeedKnots());
        existing.setCurrentLocationLabel(updatedData.getCurrentLocationLabel());
        existing.setDistanceRemainingNm(updatedData.getDistanceRemainingNm());
        existing.setCommodityDescription(updatedData.getCommodityDescription());
        existing.setContainerCount(updatedData.getContainerCount());
        existing.setContainerType(updatedData.getContainerType());
        existing.setCargoWeightKg(updatedData.getCargoWeightKg());
        existing.setCurrentTempC(updatedData.getCurrentTempC());
        existing.setTempStatus(updatedData.getTempStatus());
        existing.setManifestUrl(updatedData.getManifestUrl());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
