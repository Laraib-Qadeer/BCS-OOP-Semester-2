public class UserInfo {
    private static String UserName = "Laraib-Qadeer"; 
    private static String Password = "FA24-BCS-047"; 

    public static boolean CheckUserInfo(String inputUsername, String inputPassword) {
        return inputUsername.equals(UserName) && inputPassword.equals(Password);
    }
}
