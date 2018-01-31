import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BountyBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){

            String messagText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage().setChatId(chatId).setText(messagText);

            try{
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }

        }

    }


    public String getBotUsername() {
        return "Bounty Bot";
    }

    public String getBotToken() {
        return "524626702:AAHt_wLkXtHeXm3-dCguoMrSNwqhmz_Vy0M";
    }
}
