package com.bit.mango.salesmarketing.channel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


/**
 * Maps to the "sales_channels" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "sales_channels")
public class SalesChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Integer channelId;

    @NotBlank(message = "Channel name is required")
    @Column(name = "name")
    private String name;

    // ---- Getters and setters ----

    public Integer getChannelId() { return channelId; }
    public void setChannelId(Integer channelId) { this.channelId = channelId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
