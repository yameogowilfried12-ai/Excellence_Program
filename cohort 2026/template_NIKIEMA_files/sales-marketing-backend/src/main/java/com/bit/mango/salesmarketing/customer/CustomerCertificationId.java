package com.bit.mango.salesmarketing.customer;

import java.io.Serializable;
import java.util.Objects;

/**
 * customer_certifications is a "junction table" - it only exists to
 * link a customer to a certification (many-to-many: one customer can
 * need several certifications, one certification can apply to several
 * customers). It has no SERIAL id of its own - its primary key is the
 * COMBINATION of customer_id + certification_id.
 *
 * JPA needs a small helper class like this one to represent that
 * "2 columns together = 1 primary key" idea. You won't need to touch
 * this file again once it's set up - just know it exists because of
 * the composite key.
 */
public class CustomerCertificationId implements Serializable {

    private Integer customerId;
    private Integer certificationId;

    public CustomerCertificationId() {}

    public CustomerCertificationId(Integer customerId, Integer certificationId) {
        this.customerId = customerId;
        this.certificationId = certificationId;
    }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getCertificationId() { return certificationId; }
    public void setCertificationId(Integer certificationId) { this.certificationId = certificationId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCertificationId that)) return false;
        return Objects.equals(customerId, that.customerId) &&
               Objects.equals(certificationId, that.certificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, certificationId);
    }
}
