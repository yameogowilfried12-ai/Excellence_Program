package com.bit.mango.salesmarketing.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<DemandForecast> findByStatus(String status);
}
