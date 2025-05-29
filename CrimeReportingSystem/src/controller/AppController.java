package controller;

import models.*;
import services.AuthServices;
import services.CrimeServices;
import services.NotificationService;
import utils.FileStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AppController {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthServices authService = new AuthServices();
    private final CrimeServices crimeService = new CrimeServices();
    private User currentUser = null;

    public void run() {
        showWelcomeMenu();
        showMainMenu();
    }

    private void showWelcomeMenu() {
        System.out.println("=== Welcome to Crime Reporting System ===");

        while (currentUser == null) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Report a Crime Anonymously");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleRegistration();
                case 2 -> handleLogin();
                case 3 -> handleAnonymousReport();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void handleRegistration() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        String role = "citizen";

        currentUser = authService.register(name, email, password, role);
        if (currentUser != null) {
            System.out.println(" Registration successful!");
        }
    }

    private void handleLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        currentUser = authService.login(email, password);

        if (currentUser != null) {
            System.out.println(" Login successful! Welcome " + currentUser.getName());
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private void handleAnonymousReport() {
        System.out.println("Anonymous Report Selected");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();

        List<String> attachments = readAttachments();

        var report = crimeService.submitReport("anonymous", title, description, location, attachments);
        System.out.println("Anonymous report submitted with ID: " + report.getReportId());

        FileStorage.writeToFile(new File("db/report_log.txt"),
                "Anonymous report submitted: " + title + "\n");
    }

    private void showMainMenu() {
        if (currentUser instanceof Citizen) {
            showCitizenMenu();
        } else if (currentUser instanceof Admin) {
            showAdminMenu();
        } else if (currentUser instanceof Police) {
            showPoliceMenu();
        }
    }

    private void showCitizenMenu() {
        boolean exit = false;
        while (!exit) {
            currentUser.displayMenu();
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> handleRegularReport();
                case 2 -> handleViewOwnReports();
                case 3 -> handleEmergencyReport();
                case 4 -> handleExportMyReports();
                case 0 -> exit = true;
                default -> System.out.println(" Invalid option.");
            }
        }
    }

    private void showAdminMenu() {
        boolean exit = false;
        while (!exit) {
            currentUser.displayMenu();
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> handleViewAllReports();
                case 2 -> handleStatusUpdate();
                case 3 -> handleExportReports();
                case 4 -> handleCreatePoliceAccount();
                case 0 -> exit = true;
                default -> System.out.println(" Invalid option.");
            }
        }
    }

    private void showPoliceMenu() {
        boolean exit = false;
        while (!exit) {
            currentUser.displayMenu();
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> handleViewAllReports();
                case 2 -> handleStatusUpdate();
                case 3 -> handleExportReports();
                case 0 -> exit = true;
                default -> System.out.println(" Invalid option.");
            }
        }
    }

    public void handleRegularReport() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();

        List<String> attachments = readAttachments();

        var report = crimeService.submitReport(currentUser.getId(), title, description, location, attachments);
        System.out.println(" Report submitted with ID: " + report.getReportId());

        NotificationService.sendEmail(currentUser.getEmail(), "Report Submitted",
                "Your report titled '" + title + "' has been successfully submitted.");

        FileStorage.writeToFile(new File("db/report_log.txt"),
                "Report submitted: " + title + " | By: " + currentUser.getEmail() + "\n");
    }

    public void handleViewOwnReports() {
        var reports = crimeService.getReportsByUser(currentUser.getId());
        if (reports.isEmpty()) {
            System.out.println("No reports found.");
        } else {
            reports.forEach(System.out::println);
        }
    }

    public void handleExportMyReports() {
        List<CrimeReport> myReports = crimeService.getReportsByUser(currentUser.getId());
        if (myReports.isEmpty()) {
            System.out.println("No reports to export.");
            return;
        }

        System.out.print("Enter filename to export (e.g., reports.txt): ");
        String filename = scanner.nextLine();
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }

        String fullPath = "db/" + filename;
        crimeService.exportReportsToTextFile(fullPath, myReports);
        System.out.println(" Your report(s) have been saved to: " + fullPath);

        System.out.print("Do you want to view the exported file now? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            String content = FileStorage.readFromFile(new File(fullPath));
            System.out.println("\n=== Exported Report Content ===\n" + content);
        }
    }

    public void handleViewAllReports() {
        crimeService.getAllReports().forEach(System.out::println);
    }

    public void handleStatusUpdate() {
        System.out.print("Enter Report ID: ");
        String reportId = scanner.nextLine();
        System.out.print("New Status (Verified/Rejected): ");
        String newStatus = scanner.nextLine();
        boolean updated = crimeService.updateStatus(reportId, newStatus);
        System.out.println(updated ? " Status updated." : "Report not found.");
    }

    public void handleEmergencyReport() {
        System.out.print("Brief description of emergency: ");
        String emergencyDesc = scanner.nextLine();
        String emergencyLocation = "User's current location (not tracked in console version)";
        List<String> emergencyFiles = readAttachments();

        var emergencyReport = crimeService.submitReport(currentUser.getId(), "Emergency Alert",
                emergencyDesc, emergencyLocation, emergencyFiles);

        System.out.println("Emergency alert submitted! Report ID: " + emergencyReport.getReportId());

        NotificationService.sendSMS(currentUser.getEmail(),
                "Emergency alert submitted. Our team will respond shortly.");

        FileStorage.writeToFile(new File("db/report_log.txt"),
                "Emergency alert filed by: " + currentUser.getEmail() + " | " + emergencyDesc + "\n");
    }

    public void handleExportReports() {
        System.out.print("Enter filename to export (e.g., reports.txt): ");
        String filename = scanner.nextLine();
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }
        crimeService.exportReportsToTextFile("db/" + filename);
    }

    public void handleCreatePoliceAccount() {
        System.out.println("Create New Police Account");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User newPolice = new Police(UUID.randomUUID().toString(), name, email, password);
        authService.getAllUsers().add(newPolice);

        System.out.println("Saving police to users.json...");
        authService.saveUsers();
        FileStorage.writeToFile(new File("db/user_log.txt"),
                "Police registered by Admin: " + name + " (" + email + ")\n");
        System.out.println(" Police account created.");
    }

    private List<String> readAttachments() {
        List<String> files = new ArrayList<>();
        System.out.print("Enter number of media files to attach: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter file name #" + i + ": ");
            files.add(scanner.nextLine());
        }
        return files;
    }
}
