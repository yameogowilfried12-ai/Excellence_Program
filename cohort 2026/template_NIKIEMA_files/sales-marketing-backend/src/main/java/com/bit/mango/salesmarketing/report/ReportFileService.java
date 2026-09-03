package com.bit.mango.salesmarketing.report;

import com.bit.mango.salesmarketing.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportFileService {

    private final ReportFileRepository repository;

    public ReportFileService(ReportFileRepository repository) {
        this.repository = repository;
    }

    public List<ReportFile> getAll() {
        return repository.findAll();
    }

    public ReportFile getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReportFile not found with id: " + id));
    }

    public ReportFile create(ReportFile item) {
        return repository.save(item);
    }

    /**
     * Copies every editable field from updatedData onto the existing
     * row, then saves it. The id and created_at (if any) are left
     * untouched on purpose - those should never change after creation.
     */
    public ReportFile update(Integer id, ReportFile updatedData) {
        ReportFile existing = getById(id);
        existing.setReportId(updatedData.getReportId());
        existing.setFormat(updatedData.getFormat());
        existing.setFileUrl(updatedData.getFileUrl());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
