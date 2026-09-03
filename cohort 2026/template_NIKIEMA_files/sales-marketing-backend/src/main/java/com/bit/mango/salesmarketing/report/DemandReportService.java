package com.bit.mango.salesmarketing.report;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DemandReportService {

    private final DemandReportRepository repository;

    public DemandReportService(DemandReportRepository repository) {
        this.repository = repository;
    }

    public List<DemandReport> getAll() {
        return repository.findAll();
    }

    public DemandReport getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DemandReport not found with id: " + id));
    }

    public DemandReport create(DemandReport item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public DemandReport update(Integer id, DemandReport updatedData) {
        DemandReport existing = getById(id);
        existing.setReportType(updatedData.getReportType());
        existing.setPeriodStart(updatedData.getPeriodStart());
        existing.setPeriodEnd(updatedData.getPeriodEnd());
        existing.setTotalForecastedVolumeT(updatedData.getTotalForecastedVolumeT());
        existing.setTotalActualVolumeT(updatedData.getTotalActualVolumeT());
        existing.setVariancePct(updatedData.getVariancePct());
        existing.setStatus(updatedData.getStatus());
        existing.setIsScheduled(updatedData.getIsScheduled());
        existing.setNextRunAt(updatedData.getNextRunAt());
        existing.setScheduleFrequency(updatedData.getScheduleFrequency());
        existing.setSummary(updatedData.getSummary());
        existing.setGeneratedBy(updatedData.getGeneratedBy());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
