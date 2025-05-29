package services;

public class NotificationService {

    public static void sendEmail(String to, String subject, String message) {
        System.out.println("\n Email Sent To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("------------------------------");
    }

    public static void sendSMS(String to, String message) {
        System.out.println("\n SMS Sent To: " + to);
        System.out.println("Message: " + message);
        System.out.println("------------------------------");
    }
}
