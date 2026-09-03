package com.bit.mango.salesmarketing.campaign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignMetricRepository extends JpaRepository<CampaignMetric, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<CampaignMetric> findByStatus(String status);
}
