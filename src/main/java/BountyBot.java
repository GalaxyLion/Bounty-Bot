import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;



public class BountyBot extends TelegramLongPollingBot {

    private long chatIdGlobal;

    public void onUpdateReceived(Update update) {

        long chatId = update.getMessage().getChatId();

        chatIdGlobal = chatId;

        if (update.hasMessage() && update.getMessage().hasText()) {

            //String for Logs
            String userFirstName = update.getMessage().getChat().getFirstName();
            String userLastName = update.getMessage().getChat().getLastName();
            String userUsername = update.getMessage().getChat().getUserName();
            long userId = update.getMessage().getChat().getId();

            String messageText = update.getMessage().getText();


            if (messageText.equals("/start")) {

                SendPhoto messagePhoto = Messages.returnStartMessage(chatId);
                //Create markup keyboard
                ReplyKeyboardMarkup keyboardMarkup = KeyboardBot.createKeyboard();
                messagePhoto.setReplyMarkup(keyboardMarkup);

                Logs.getLogs(userFirstName,userLastName ,userUsername, Long.toString(userId), messageText, messagePhoto.getCaption());
                try {
                    sendPhoto(messagePhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if(messageText.contains("Краны") || messageText.contains("Bounty") || messageText.contains("AirDrop")){

                SendMessage message = Messages.returnUrl(chatId,messageText);

                Logs.getLogs(userFirstName,userLastName, userUsername, Long.toString(userId),  messageText, message.getText());

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            }else{

                SendMessage message = Messages.returnDefaultMessage(chatId);

                Logs.getLogs(userFirstName,userLastName, userUsername, Long.toString(userId), messageText, message.getText());

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }


        }







        /*else if (update.hasMessage() && update.getMessage().hasPhoto()) {

            long chatId = update.getMessage().getChatId();

            String fileIden = "AgADAgADY6gxGw75wEt8miHTtTtVlrj1Aw4ABAzg2-QyuFmPDNgCAAEC";
            String fileIden2 = "AgADAgADZqgxGw75wEvxl0_fTizzFIMJnA4ABJFrk6XlG0ztkvsAAgI";

            List<PhotoSize> photos = update.getMessage().getPhoto();


            String fileId = photos.get(0).getFileId();

            int f_width = photos.get(0).getWidth();

            int f_height = photos.get(0).getHeight();
            // Set photo caption
            String caption = "file_id: " + fileId + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
            SendPhoto msg = new SendPhoto()
                    .setChatId(chatId)
                    .setPhoto(fileId)
                    .setCaption(caption);
            try {
                sendPhoto(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();

            }
        }*/


    }

    public void updateUrls() throws TelegramApiException{

        String [] faucet = {"http://telegra.ph/Aktualnye-krany-kriptovalyut-02-03" ,"Кранов"};
        String [] bounty = {"http://telegra.ph/Aktualnye-Bounty-kriptovalyut-02-14", "Bounty"};
        String [] airDrop = {"http://telegra.ph/Aktualnye-AirDrop-02-20", "AirDrop"};
        ParsingPages parsingPages = new ParsingPages();
        parsingPages.parsePage(faucet[0]);
        parsingPages.parsePage(bounty[0]);
        parsingPages.parsePage(airDrop[0]);



        while (true) {
            if (parsingPages.returnUrl().equals(faucet[0]) && parsingPages.getReturn()) {

                if(chatIdGlobal!=0) {
                    execute(parsingPages.updateInfoUrl(chatIdGlobal, faucet[1], faucet[0]));
                }
            }

            if(parsingPages.returnUrl().equals(bounty[0]) && parsingPages.getReturn()){
                if(chatIdGlobal!=0) {
                    execute(parsingPages.updateInfoUrl(chatIdGlobal, bounty[1], bounty[0]));
                }
            }
            if(parsingPages.returnUrl().equals(airDrop[0]) && parsingPages.getReturn()){
                if(chatIdGlobal!=0) {
                    execute(parsingPages.updateInfoUrl(chatIdGlobal, airDrop[1], airDrop[0]));
                }
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
