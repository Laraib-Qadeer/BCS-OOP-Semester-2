package models;

import java.time.LocalDateTime;
import java.util.List;

public class CrimeReport {
    private String reportId;
    private String reporterId;
    private String title;
    private String description;
    private String location;
    private LocalDateTime timestamp;
    private String status;
    private List<String> attachments;

    public CrimeReport(String reportId, String reporterId, String title, String description, String location, List<String> attachments) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.attachments = attachments;
        this.timestamp = LocalDateTime.now();
        this.status = "Pending";
    }

    public String getReportId() {
        return reportId;
    }
    public String getReporterId() {
        return reporterId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getStatus() {
        return status;
    }
    public List<String> getAttachments() {
        return attachments;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String attachCount = (attachments != null) ? attachments.size() + " file(s)" : "No files";
        return "[Report: " + title + " | Status: " + status + " | Media: " + attachCount + "]";
    }
}
