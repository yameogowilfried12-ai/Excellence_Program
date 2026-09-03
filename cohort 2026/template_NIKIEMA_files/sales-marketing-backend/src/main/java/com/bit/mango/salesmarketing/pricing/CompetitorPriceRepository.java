package com.bit.mango.salesmarketing.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorPriceRepository extends JpaRepository<CompetitorPrice, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<CompetitorPrice> findByStatus(String status);
}
