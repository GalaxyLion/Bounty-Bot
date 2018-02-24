import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logs {

    public static void getLogs(String firstName, String lastName, String userLogin, String userId, String text, String botAnswer){
        System.out.println("\n ==========================");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + firstName + " " + lastName + ". User login is " + userLogin + ". (id = " + userId + ") \n" +
                "Text - " + text);
        System.out.println("Bot answer : \n" +
                "Text - " + botAnswer);
    }

}
