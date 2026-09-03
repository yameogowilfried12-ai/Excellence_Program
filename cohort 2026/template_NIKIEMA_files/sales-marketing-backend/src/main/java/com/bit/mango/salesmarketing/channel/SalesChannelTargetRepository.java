package com.bit.mango.salesmarketing.channel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesChannelTargetRepository extends JpaRepository<SalesChannelTarget, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<SalesChannelTarget> findByStatus(String status);
}
