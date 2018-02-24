import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;


public class Messages {

    public static SendPhoto returnStartMessage(long chatId){
        SendPhoto messagePhoto = new SendPhoto()
                .setChatId(chatId)
                .setPhoto("AgADAgADaqgxGw75wEvuf6DD5CksepsNnA4ABBJZSXVsKcN6HvoAAgI")
                .setCaption("Добро пожаловать в BountyAirDrop_Bot. \n" +
                        "Это самый простой способ узнать информацию об актуальных Bounty компаниях, различных AirDrop, а также криптовалютных кранах.");
        return messagePhoto;
    }

    public static SendMessage returnUrl(long chatId, String messageText) {
        String messageReturn = "";
        if (messageText.contains("Краны")) {
            messageReturn = "Вот актуальная ссылка для Вас \n" +
                    "http://telegra.ph/Aktualnye-krany-kriptovalyut-02-03";
        }else if(messageText.contains("Bounty")){
            messageReturn = "Вот актуальная ссылка для Вас \n" +
                    "http://telegra.ph/Aktualnye-Bounty-kriptovalyut-02-14";
        }else if(messageText.contains("AirDrop")){
            messageReturn = "Еще не готово";
        }

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(messageReturn);
        return message;
    }

    public static SendMessage returnDefaultMessage(long chatId){
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(EmojiParser.parseToUnicode("Такой команды у меня нет :cry:"));
        return message;
    }

    public static SendMessage returnUpdatedUrl(long chatId, String tapsBountyAir, String url){
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(EmojiParser.parseToUnicode("Привет, обновилась информация на странице "
                        + tapsBountyAir+ "\n" +
                        "Рекомендую посмотреть :wink: \n" +
                        url));
        return message;
    }



}
