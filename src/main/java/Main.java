import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        //ParsingPages parsePages = new ParsingPages();


        try {
           // parsePages.parseTap();
            BountyBot bountyBot = new BountyBot();
            botsApi.registerBot(bountyBot);
            bountyBot.updateUrls();
        }catch (TelegramApiException ex){
            ex.printStackTrace();
        }


    }

}
