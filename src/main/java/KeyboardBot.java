import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBot {

    public static ReplyKeyboardMarkup createKeyboard(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        String buttonText = EmojiParser.parseToUnicode(" :potable_water: Краны");
        row.add(buttonText);
        buttonText = EmojiParser.parseToUnicode(":moneybag: Bounty");
        row.add(buttonText);
        buttonText = EmojiParser.parseToUnicode(":free: AirDrop");
        row.add(buttonText);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

}
