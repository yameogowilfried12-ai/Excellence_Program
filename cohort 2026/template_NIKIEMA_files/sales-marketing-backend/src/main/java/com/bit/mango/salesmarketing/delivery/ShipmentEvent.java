package com.bit.mango.salesmarketing.delivery;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Maps to the "shipment_events" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "shipment_events")
public class ShipmentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @NotNull(message = "shipmentId is required")
    @Column(name = "shipment_id")
    private Integer shipmentId;  // FK -> shipments

    @NotBlank(message = "step is required")
    @Column(name = "step")
    private String step;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Column(name = "note")
    private String note;

    // ---- Getters and setters ----

    public Integer getEventId() { return eventId; }
    public void setEventId(Integer eventId) { this.eventId = eventId; }

    public Integer getShipmentId() { return shipmentId; }
    public void setShipmentId(Integer shipmentId) { this.shipmentId = shipmentId; }

    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }

    public LocalDateTime getEventTime() { return eventTime; }
    public void setEventTime(LocalDateTime eventTime) { this.eventTime = eventTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
