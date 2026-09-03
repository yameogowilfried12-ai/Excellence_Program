package com.bit.mango.salesmarketing.alert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<Alert> findByStatus(String status);
}
