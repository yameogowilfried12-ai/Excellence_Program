package com.bit.mango.salesmarketing.campaign;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

/**
 * Maps to the "campaign_metrics" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "campaign_metrics")
public class CampaignMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metric_id")
    private Integer metricId;

    @NotNull(message = "campaignId is required")
    @Column(name = "campaign_id")
    private Integer campaignId;  // FK -> campaigns

    @Column(name = "metric_date")
    private LocalDate metricDate;

    @PositiveOrZero
    @Column(name = "clicks")
    private Integer clicks;

    @PositiveOrZero
    @Column(name = "signups")
    private Integer signups;

    @PositiveOrZero
    @Column(name = "reach")
    private Integer reach;

    // ---- Getters and setters ----

    public Integer getMetricId() { return metricId; }
    public void setMetricId(Integer metricId) { this.metricId = metricId; }

    public Integer getCampaignId() { return campaignId; }
    public void setCampaignId(Integer campaignId) { this.campaignId = campaignId; }

    public LocalDate getMetricDate() { return metricDate; }
    public void setMetricDate(LocalDate metricDate) { this.metricDate = metricDate; }

    public Integer getClicks() { return clicks; }
    public void setClicks(Integer clicks) { this.clicks = clicks; }

    public Integer getSignups() { return signups; }
    public void setSignups(Integer signups) { this.signups = signups; }

    public Integer getReach() { return reach; }
    public void setReach(Integer reach) { this.reach = reach; }
}
