package com.bit.mango.salesmarketing.channel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Maps to the "sales_channel_targets" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "sales_channel_targets")
public class SalesChannelTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Integer targetId;

    @NotNull(message = "channelId is required")
    @Column(name = "channel_id")
    private Integer channelId;  // FK -> sales_channels

    @NotNull(message = "periodStart is required")
    @Column(name = "period_start")
    private LocalDate periodStart;

    @NotNull(message = "periodEnd is required")
    @Column(name = "period_end")
    private LocalDate periodEnd;

    @NotNull(message = "revenueTargetEur is required")
    @PositiveOrZero
    @Column(name = "revenue_target_eur")
    private BigDecimal revenueTargetEur;

    // ---- Getters and setters ----

    public Integer getTargetId() { return targetId; }
    public void setTargetId(Integer targetId) { this.targetId = targetId; }

    public Integer getChannelId() { return channelId; }
    public void setChannelId(Integer channelId) { this.channelId = channelId; }

    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate periodStart) { this.periodStart = periodStart; }

    public LocalDate getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDate periodEnd) { this.periodEnd = periodEnd; }

    public BigDecimal getRevenueTargetEur() { return revenueTargetEur; }
    public void setRevenueTargetEur(BigDecimal revenueTargetEur) { this.revenueTargetEur = revenueTargetEur; }
}
