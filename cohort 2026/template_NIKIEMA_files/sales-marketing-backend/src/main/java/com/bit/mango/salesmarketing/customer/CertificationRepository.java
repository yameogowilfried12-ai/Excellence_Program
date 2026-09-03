package com.bit.mango.salesmarketing.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    // Basic CRUD (findAll, findById, save, deleteById...) comes free
    // from JpaRepository. Add custom finder methods here as needed,
    // e.g.: List<Certification> findByStatus(String status);
}
