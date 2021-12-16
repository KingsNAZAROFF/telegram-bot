package uz.bots.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import uz.bots.Ticket;
import uz.bots.model.Admin;
import uz.bots.model.User;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Author: Muhammad
 * Date: 11.12.2021
 * Time: 19:23
 */
public class AdminService {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.HH.yyyy");

    public String adminMenu(Message message) {
        String sendText = "not found";
        Admin admin = TelegramBot.admins.get(message.getFrom().getId());
        switch (admin.getStep()) {
            case 0:
                sendText = "Xush kebsiz!";
                TelegramBot.admins.get(message.getFrom().getId()).setStep(1);
                break;
            case 1:
                if (message.getText().equals("Murojatlar")) {
                    sendText = "";
                    for (Long id : TelegramBot.users.keySet()) {
                        User user = TelegramBot.users.get(id);
                        if (user.getCompleted()) {
                            sendText += "Ismi: "
                                    + user.getUserName() + "\nTel: +"
                                    + user.getTelephoneNumber() + "\nKonsert: "
                                    + user.getBiletName() + "\nTg name: "
                                    + user.getTgName() + "\nVaqti: "
                                    + simpleDateFormat.format(user.getDatePart()) + "\nID: "
                                    + user.getTgId() + "\n" +
                                    "=============================" + "\n";
                        }
                    }
                } else if (message.getText().equals("Murojat yopish")) {
                    sendText = "Foydalanuvchining id raqamini kiriting";
                    TelegramBot.admins.get(message.getFrom().getId()).setStep(2);
                }
                break;
            case 2:
                if (message.getText().equals("Ortga qaytish")) {
                    sendText = "Asosiy menu";
                    TelegramBot.admins.get(message.getFrom().getId()).setStep(1);
                } else {
                    if (TelegramBot.users.remove(Long.parseLong(message.getText())) != null) {
                        sendText = "Murojat o'chirildi";
                    } else {
                        sendText = "Nimadur xatolik ketti\nYoki ID raqam xato";
                    }
                }
                break;
        }

        return sendText;
    }
}
