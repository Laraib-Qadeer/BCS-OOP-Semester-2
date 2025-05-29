package services;

import com.google.gson.reflect.TypeToken;
import models.*;
import utils.FileStorage;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeServices {
    private List<CrimeReport> reports;
    private final String REPORT_FILE = "db/reports.json";
    private final String REPORT_LOG_FILE = "db/report_log.txt";

    public CrimeServices() {
        Type reportListType = new TypeToken<List<CrimeReport>>() {}.getType();
        reports = FileStorage.loadFromFile(REPORT_FILE, reportListType);
        if (reports == null) {
            reports = new ArrayList<>();
        }
    }


    public CrimeReport submitReport(String reporterId, String title, String description, String location, List<String> attachments) {
        String id = UUID.randomUUID().toString();
        CrimeReport report = new CrimeReport(id, reporterId, title, description, location, attachments);
        reports.add(report);
        saveReports();

        String logLine = " Report submitted: " + title + " | By: " + reporterId + " | Status: Pending\n";
        FileStorage.writeToFile(new File(REPORT_LOG_FILE), logLine);

        return report;
    }

    public CrimeReport submitReport(String reporterId, String title, String description, String location) {
        return submitReport(reporterId, title, description, location, new ArrayList<>());
    }

    public List<CrimeReport> getAllReports() {
        return reports;
    }

    public List<CrimeReport> getReportsByUser(String userId) {
        List<CrimeReport> userReports = new ArrayList<>();
        for (CrimeReport r : reports) {
            if (r.getReporterId().equals(userId)) {
                userReports.add(r);
            }
        }
        return userReports;
    }

    public boolean updateStatus(String reportId, String newStatus) {
        for (CrimeReport report : reports) {
            if (report.getReportId().equals(reportId)) {
                report.setStatus(newStatus);
                saveReports();

                String logLine = " Status updated: " + report.getTitle() + " → " + newStatus + " | ID: " + reportId + "\n";
                FileStorage.writeToFile(new File(REPORT_LOG_FILE), logLine);

                User user = getUserById(report.getReporterId());
                if (user != null && !user.getId().equals("anonymous")) {
                    NotificationService.sendEmail(
                            user.getEmail(),
                            "Report Status Updated",
                            "Your report titled '" + report.getTitle() + "' is now marked as: " + newStatus
                    );
                }

                return true;
            }
        }
        return false;
    }

    public void exportReportsToTextFile(String filePath) {
        try {
            List<CrimeReport> allReports = getAllReports();
            StringBuilder builder = new StringBuilder();

            for (CrimeReport r : allReports) {
                builder.append("Report ID: ").append(r.getReportId()).append("\n");
                builder.append("Title: ").append(r.getTitle()).append("\n");
                builder.append("Description: ").append(r.getDescription()).append("\n");
                builder.append("Location: ").append(r.getLocation()).append("\n");
                builder.append("Status: ").append(r.getStatus()).append("\n");
                builder.append("Reporter ID: ").append(r.getReporterId()).append("\n");
                builder.append("Attachments: ").append(r.getAttachments()).append("\n");
                builder.append("Timestamp: ").append(r.getTimestamp()).append("\n");
                builder.append("--------------------------------------------------\n");
            }

            Files.write(Paths.get(filePath), builder.toString().getBytes());
            System.out.println(" Reports exported to: " + filePath);
        } catch (Exception e) {
            System.out.println(" Failed to export reports: " + e.getMessage());
        }
    }
    public void exportReportsToTextFile(String filePath, List<CrimeReport> reportList) {
        try {
            StringBuilder builder = new StringBuilder();

            for (CrimeReport r : reportList) {
                builder.append("Report ID: ").append(r.getReportId()).append("\n");
                builder.append("Title: ").append(r.getTitle()).append("\n");
                builder.append("Description: ").append(r.getDescription()).append("\n");
                builder.append("Location: ").append(r.getLocation()).append("\n");
                builder.append("Status: ").append(r.getStatus()).append("\n");
                builder.append("Reporter ID: ").append(r.getReporterId()).append("\n");
                builder.append("Attachments: ").append(r.getAttachments()).append("\n");
                builder.append("Timestamp: ").append(r.getTimestamp()).append("\n");
                builder.append("--------------------------------------------------\n");
            }

            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), builder.toString().getBytes());
            System.out.println(" Reports exported to: " + filePath);
        } catch (Exception e) {
            System.out.println(" Failed to export reports: " + e.getMessage());
        }
    }

    private void saveReports() {
        FileStorage.saveToFile(reports, REPORT_FILE);
    }

    private User getUserById(String id) {
        AuthServices authService = new AuthServices();
        for (User u : authService.getAllUsers()) {
            if (u.getId().equals(id)) {
                switch (u.getRole().toLowerCase()) {
                    case "admin" -> {
                        return new Admin(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                    }
                    case "police" -> {
                        return new Police(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                    }
                    case "citizen" -> {
                        return new Citizen(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                    }
                }
            }
        }
        return null;
    }
}
