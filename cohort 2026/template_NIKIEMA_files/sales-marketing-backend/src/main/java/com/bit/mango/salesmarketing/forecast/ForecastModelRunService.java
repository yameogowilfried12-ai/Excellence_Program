package com.bit.mango.salesmarketing.forecast;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ForecastModelRunService {

    private final ForecastModelRunRepository repository;

    public ForecastModelRunService(ForecastModelRunRepository repository) {
        this.repository = repository;
    }

    public List<ForecastModelRun> getAll() {
        return repository.findAll();
    }

    public ForecastModelRun getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ForecastModelRun not found with id: " + id));
    }

    public ForecastModelRun create(ForecastModelRun item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public ForecastModelRun update(Integer id, ForecastModelRun updatedData) {
        ForecastModelRun existing = getById(id);
        existing.setCropVariety(updatedData.getCropVariety());
        existing.setRegion(updatedData.getRegion());
        existing.setConfidenceIntervalPct(updatedData.getConfidenceIntervalPct());
        existing.setUseWeatherData(updatedData.getUseWeatherData());
        existing.setUseCommodityPrices(updatedData.getUseCommodityPrices());
        existing.setUseGeopoliticalIndex(updatedData.getUseGeopoliticalIndex());
        existing.setModelStatus(updatedData.getModelStatus());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
