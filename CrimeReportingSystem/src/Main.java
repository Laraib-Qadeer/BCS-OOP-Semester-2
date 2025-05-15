import models.User;
import models.CrimeReport;
import services.AuthServices;
import services.CrimeServices;
import services.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthServices authService = new AuthServices();
        CrimeServices crimeService = new CrimeServices();

        System.out.println("=== Welcome to Crime Reporting System ===");

        User currentUser = null;

        while (currentUser == null) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Report a Crime Anonymously");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                System.out.print("Role (admin/police/citizen): ");
                String role = scanner.nextLine();

                currentUser = authService.register(name, email, password, role);
                System.out.println("Registration successful!");

            } else if (choice == 2) {
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                currentUser = authService.login(email, password);
                if (currentUser != null) {
                    System.out.println("Login successful! Welcome " + currentUser.getName());
                } else {
                    System.out.println("Invalid email or password.");
                }

            } else if (choice == 3) {
                System.out.println("Anonymous Report Selected");
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Description: ");
                String description = scanner.nextLine();
                System.out.print("Location: ");
                String location = scanner.nextLine();

                List<String> attachments = new ArrayList<>();
                System.out.print("Enter number of media files to attach: ");
                int fileCount = scanner.nextInt();
                scanner.nextLine();
                for (int i = 1; i <= fileCount; i++) {
                    System.out.print("Enter file name #" + i + ": ");
                    attachments.add(scanner.nextLine());
                }

                CrimeReport report = crimeService.submitReport(
                        "anonymous", title, description, location, attachments
                );
                System.out.println("Anonymous report submitted with ID: " + report.getReportId());

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Submit Crime Report");
            System.out.println("2. View My Reports");
            if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("police")) {
                System.out.println("3. View All Reports");
                System.out.println("4. Update Report Status");
                System.out.println("5. Export Reports to Text File");
            }
            System.out.println("6. Emergency Assistance (Send Alert)");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();

                    List<String> attachments = new ArrayList<>();
                    System.out.print("Enter number of media files to attach: ");
                    int fileCount = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 1; i <= fileCount; i++) {
                        System.out.print("Enter file name #" + i + ": ");
                        attachments.add(scanner.nextLine());
                    }

                    CrimeReport report = crimeService.submitReport(
                            currentUser.getId(), title, description, location, attachments
                    );

                    System.out.println("Report submitted with ID: " + report.getReportId());

                    NotificationService.sendEmail(
                            currentUser.getEmail(),
                            "Report Submitted",
                            "Your report titled '" + title + "' has been successfully submitted."
                    );
                    break;

                case 2:
                    List<CrimeReport> myReports = crimeService.getReportsByUser(currentUser.getId());
                    if (myReports.isEmpty()) {
                        System.out.println("No reports found.");
                    } else {
                        for (CrimeReport r : myReports) {
                            System.out.println(r);
                        }
                    }
                    break;

                case 3:
                    if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("police")) {
                        List<CrimeReport> allReports = crimeService.getAllReports();
                        for (CrimeReport r : allReports) {
                            System.out.println(r);
                        }
                    } else {
                        System.out.println("Unauthorized access.");
                    }
                    break;

                case 4:
                    if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("police")) {
                        System.out.print("Enter Report ID: ");
                        String reportId = scanner.nextLine();
                        System.out.print("New Status (Verified/Rejected): ");
                        String newStatus = scanner.nextLine();

                        boolean updated = crimeService.updateStatus(reportId, newStatus);
                        if (updated) {
                            System.out.println("Status updated.");
                        } else {
                            System.out.println("Report not found.");
                        }
                    } else {
                        System.out.println("Unauthorized access.");
                    }
                    break;

                case 6:
                    System.out.print("Brief description of emergency: ");
                    String emergencyDesc = scanner.nextLine();
                    String emergencyLocation = "User's current location (not tracked in console version)";

                    List<String> emergencyFiles = new ArrayList<>();
                    System.out.print("Enter number of media files to attach: ");
                    int emergencyFileCount = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 1; i <= emergencyFileCount; i++) {
                        System.out.print("Enter file name #" + i + ": ");
                        emergencyFiles.add(scanner.nextLine());
                    }

                    CrimeReport emergencyReport = crimeService.submitReport(
                            currentUser.getId(), "🚨 Emergency Alert", emergencyDesc, emergencyLocation, emergencyFiles
                    );

                    System.out.println("Emergency alert submitted! Report ID: " + emergencyReport.getReportId());

                    NotificationService.sendSMS(
                            currentUser.getEmail(),
                            "Emergency alert submitted. Our team will respond shortly."
                    );
                    break;

                case 5:
                    if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("police")) {
                        System.out.print("Enter filename to export (e.g., reports.txt): ");
                        String filename = scanner.nextLine();
                        String exportPath = "db/" + filename;
                        crimeService.exportReportsToTextFile(exportPath);
                    } else {
                        System.out.println("Unauthorized access.");
                    }
                    break;

                case 0:
                    exit = true;
                    System.out.println("Thank you for using the Crime Reporting System!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
