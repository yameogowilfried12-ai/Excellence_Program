package com.bit.mango.salesmarketing.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandReportRepository extends JpaRepository<DemandReport, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<DemandReport> findByStatus(String status);
}
