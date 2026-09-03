package com.bit.mango.salesmarketing.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingHistoryRepository extends JpaRepository<PricingHistory, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<PricingHistory> findByStatus(String status);
}
