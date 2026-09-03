package com.bit.mango.salesmarketing.customer;

import jakarta.persistence.*;

/**
 * Maps to the "customer_certifications" junction table.
 * @IdClass tells JPA "use CustomerCertificationId to represent this
 * entity's primary key" (the combination of the two fields below).
 */
@Entity
@Table(name = "customer_certifications")
@IdClass(CustomerCertificationId.class)
public class CustomerCertification {

    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @Id
    @Column(name = "certification_id")
    private Integer certificationId;

    public CustomerCertification() {}

    public CustomerCertification(Integer customerId, Integer certificationId) {
        this.customerId = customerId;
        this.certificationId = certificationId;
    }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getCertificationId() { return certificationId; }
    public void setCertificationId(Integer certificationId) { this.certificationId = certificationId; }
}
