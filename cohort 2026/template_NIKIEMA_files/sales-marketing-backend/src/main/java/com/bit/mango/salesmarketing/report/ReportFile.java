package com.bit.mango.salesmarketing.report;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Maps to the "report_files" table.
 * Foreign keys are kept as plain Integer id fields (not @ManyToOne)
 * to keep things simple while learning - same approach as the rest
 * of this backend.
 */
@Entity
@Table(name = "report_files")
public class ReportFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @NotNull(message = "reportId is required")
    @Column(name = "report_id")
    private Integer reportId;  // FK -> demand_reports

    @NotBlank(message = "format is required")
    @Column(name = "format")
    private String format;

    @Column(name = "file_url")
    private String fileUrl;

    // ---- Getters and setters ----

    public Integer getFileId() { return fileId; }
    public void setFileId(Integer fileId) { this.fileId = fileId; }

    public Integer getReportId() { return reportId; }
    public void setReportId(Integer reportId) { this.reportId = reportId; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
