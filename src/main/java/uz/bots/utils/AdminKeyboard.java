package uz.bots.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.bots.model.Admin;
import uz.bots.model.User;

import java.util.ArrayList;

/**
 * Author: Muhammad
 * Date: 11.12.2021
 * Time: 19:23
 */
public class AdminKeyboard {
    public static void userKeyboard(SendMessage sendMessage, Admin admin) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        keyboardMarkup.setResizeKeyboard(true);

        ArrayList<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row1, row2, row3;
        KeyboardButton button1, button2, button3, button4, button5;

        switch (admin.getStep()) {
            case 1:
                row1 = new KeyboardRow();
                button1 = new KeyboardButton("Murojatlar");
                button2 = new KeyboardButton("Murojat yopish");
                row1.add(button1);
                row1.add(button2);
                rows.add(row1);
                break;
            case 2:
                row3 = new KeyboardRow();
                button5 = new KeyboardButton("Ortga qaytish");
                row3.add(button5);
                rows.add(row3);
                break;
        }

        keyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }
}
