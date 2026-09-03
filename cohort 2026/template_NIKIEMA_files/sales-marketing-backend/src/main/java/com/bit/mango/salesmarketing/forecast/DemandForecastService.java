package com.bit.mango.salesmarketing.forecast;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DemandForecastService {

    private final DemandForecastRepository repository;

    public DemandForecastService(DemandForecastRepository repository) {
        this.repository = repository;
    }

    public List<DemandForecast> getAll() {
        return repository.findAll();
    }

    public DemandForecast getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DemandForecast not found with id: " + id));
    }

    public DemandForecast create(DemandForecast item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public DemandForecast update(Integer id, DemandForecast updatedData) {
        DemandForecast existing = getById(id);
        existing.setProductId(updatedData.getProductId());
        existing.setForecastMonth(updatedData.getForecastMonth());
        existing.setScenario(updatedData.getScenario());
        existing.setForecastedVolumeT(updatedData.getForecastedVolumeT());
        existing.setLowerBoundT(updatedData.getLowerBoundT());
        existing.setUpperBoundT(updatedData.getUpperBoundT());
        existing.setCapacityRisk(updatedData.getCapacityRisk());
        existing.setIsActual(updatedData.getIsActual());
        existing.setModelVersion(updatedData.getModelVersion());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
