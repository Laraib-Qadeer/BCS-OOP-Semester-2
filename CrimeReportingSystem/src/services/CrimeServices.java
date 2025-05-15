package services;

import com.google.gson.reflect.TypeToken;
import models.CrimeReport;
import models.User;
import utils.FileStorage;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeServices {
    private List<CrimeReport> reports;
    private final String REPORT_FILE = "src/db/reports.json";

    public CrimeServices() {
        Type reportListType = new TypeToken<List<CrimeReport>>() {}.getType();
        reports = FileStorage.loadFromFile(REPORT_FILE, reportListType);
        if (reports == null) {
            reports = new ArrayList<>();
        }
    }
    // submit report with media
    public CrimeReport submitReport(String reporterId, String title, String description, String location, List<String> attachments) {
        String id = UUID.randomUUID().toString();
        CrimeReport report = new CrimeReport(id, reporterId, title, description, location, attachments);
        reports.add(report);
        saveReports();
        return report;
    }

    public CrimeReport submitReport(String reporterId, String title, String description, String location) {
        return submitReport(reporterId, title, description, location, new ArrayList<>());//overloaded method
    }
    //Used by admin or police to view everything.
    public List<CrimeReport> getAllReports() {
        return reports;
    }
    // view reprt by any specific user
    public List<CrimeReport> getReportsByUser(String userId) {
        List<CrimeReport> userReports = new ArrayList<>();
        for (CrimeReport r : reports) {
            if (r.getReporterId().equals(userId)) {
                userReports.add(r);// Filters the full report list to return only those filed by a specific user
            }
        }
        return userReports;
    }

    public boolean updateStatus(String reportId, String newStatus) {
        for (CrimeReport report : reports) {
            if (report.getReportId().equals(reportId)) {
                report.setStatus(newStatus);
                saveReports();

                // Notify user
                User user = getUserById(report.getReporterId());
                // prevents from sending email to anonymous reporter
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
            //Then it loops over each report and appends its details to a long string:
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
            //writing to a file:
            Files.write(Paths.get(filePath), builder.toString().getBytes());
            System.out.println("Reports exported to: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to export reports: " + e.getMessage());
        }
    }
    //save to json file
    private void saveReports() {
        FileStorage.saveToFile(reports, REPORT_FILE);
    }
    // find user who reported and u can send him notification:
    private User getUserById(String id) {
        AuthServices authService = new AuthServices();
        for (User u : authService.getAllUsers()) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }
}
