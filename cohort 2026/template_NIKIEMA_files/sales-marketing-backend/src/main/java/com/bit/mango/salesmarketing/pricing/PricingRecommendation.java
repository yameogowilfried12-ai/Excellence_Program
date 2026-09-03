package com.bit.mango.salesmarketing.pricing;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Maps to the "pricing_recommendations" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "pricing_recommendations")
public class PricingRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Integer recommendationId;

    @Column(name = "product_id")
    private Integer productId;  // FK -> products

    @NotBlank(message = "message is required")
    @Column(name = "message")
    private String message;

    @Column(name = "suggested_price_eur")
    private BigDecimal suggestedPriceEur;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ---- Getters and setters ----

    public Integer getRecommendationId() { return recommendationId; }
    public void setRecommendationId(Integer recommendationId) { this.recommendationId = recommendationId; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public BigDecimal getSuggestedPriceEur() { return suggestedPriceEur; }
    public void setSuggestedPriceEur(BigDecimal suggestedPriceEur) { this.suggestedPriceEur = suggestedPriceEur; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
