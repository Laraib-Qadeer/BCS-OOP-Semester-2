package services;

public class NotificationService {

    public static void sendEmail(String to, String subject, String message) {
        System.out.println("[Email Sent]");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("----------------------------------");
    }

    public static void sendSMS(String phone, String message) {
        System.out.println("[SMS Sent]");
        System.out.println("To: " + phone);
        System.out.println("Message: " + message);
        System.out.println("----------------------------------");
    }
}
