package com.bit.mango.salesmarketing.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Notice the ID type here is CustomerCertificationId (the composite
 * key class), not a plain Integer like in every other repository.
 */
public interface CustomerCertificationRepository
        extends JpaRepository<CustomerCertification, CustomerCertificationId> {

    List<CustomerCertification> findByCustomerId(Integer customerId);

    List<CustomerCertification> findByCertificationId(Integer certificationId);
}
