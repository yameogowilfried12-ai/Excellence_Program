package com.bit.mango.salesmarketing.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastModelRunRepository extends JpaRepository<ForecastModelRun, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<ForecastModelRun> findByStatus(String status);
}
