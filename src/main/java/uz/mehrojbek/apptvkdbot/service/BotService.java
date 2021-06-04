package uz.mehrojbek.apptvkdbot.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.mehrojbek.apptvkdbot.bot.MainBot;
import uz.mehrojbek.apptvkdbot.entity.User;
import uz.mehrojbek.apptvkdbot.entity.enums.Status;
import uz.mehrojbek.apptvkdbot.entity.enums.Type;
import uz.mehrojbek.apptvkdbot.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class BotService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetData getData;
    @Autowired
    BotButton botButton;

    public SendMessage enterPassword(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Parolni kiriting:");
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        return sendMessage;
    }

    public void saveUser(Message message, Status status) {
        User user = new User();
        user.setChatId(message.getChatId());
        user.setStatus(Status.MEMBER);
        this.userRepository.save(user);
    }

    public SendMessage start(Message message) {
        MainBot.user.put(message.getChatId(), 0);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("\ud83d\udd1d Main Menu");
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setReplyMarkup(this.botButton.startButton());
        return sendMessage;
    }

    public SendMessage firstChoice(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message.getText());
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        switch(text) {
            case "TVKD hizmat narxlari \ud83d\ude91":
                MainBot.user.put(message.getChatId(), 2);
                return this.getData.price(message);

            case "Oziq ovqat ombori \ud83e\uddc5\ud83e\udd54\ud83e\udd6c":
                MainBot.user.put(message.getChatId(), 3);
                sendMessage.setReplyMarkup(this.botButton.listFood());
                return sendMessage;

            case "dorixona \ud83d\udc8a \ud83d\udc89":
                MainBot.user.put(message.getChatId(), 4);
                sendMessage.setReplyMarkup(this.botButton.medicine());
                return sendMessage;

            case "Davolanayotgan bemorlar ro'yhxati \ud83d\udecf":
                MainBot.user.put(message.getChatId(), 1);
                sendMessage.setReplyMarkup(this.botButton.listPatient());
                return sendMessage;

            default:
                return this.secondChoice(message, text);
        }

    }


    public SendMessage secondChoice(Message message, String text) {
        SendMessage sendMessage;

        switch(text) {
            case "1 -bo'lim \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 41);
                sendMessage = new SendMessage();
                sendMessage.setText(message.getText());
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setReplyMarkup(this.botButton.innerMedicineFirst());
                return sendMessage;

            case "Muruvvat  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c":
                MainBot.user.put(message.getChatId(), 32);
                return this.getData.grace(message);

            case "2 -bo'lim \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 42);
                sendMessage = new SendMessage();
                sendMessage.setText(message.getText());
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setReplyMarkup(this.botButton.innerMedicineSecond());
                return sendMessage;

            case "\ud83d\udd1d Main Menu":
                MainBot.user.put(message.getChatId(), 0);
                return this.start(message);

            case "Intensiv palatadagi bemorlar ro'yhati":
                MainBot.user.put(message.getChatId(), 13);
                return this.getData.patients(message, 0, (Integer)null);

            case "Pullik  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c":
                MainBot.user.put(message.getChatId(), 34);
                return this.getData.paid(message);

            case "Zahira \ud83e\udd54\ud83e\udd55\ud83e\udd6c":
                MainBot.user.put(message.getChatId(), 31);
                return this.getData.reserve(message);

            case "\ud83d\udd19 Back":
                return this.back(message);

            case "2⃣- bo'lim bemorlar ro'yhati":
                MainBot.user.put(message.getChatId(), 12);
                return this.getData.patients(message, 2, (Integer)null);

            case "Imtiyoz  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c":
                MainBot.user.put(message.getChatId(), 33);
                return this.getData.privilege(message);

            case "Ichki dorixona \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 43);
                sendMessage = new SendMessage();
                sendMessage.setText(message.getText());
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setReplyMarkup(this.botButton.innerMedicineThird());
                return sendMessage;

            case "1⃣- bo'lim bemorlar ro'yhati":
                MainBot.user.put(message.getChatId(), 11);
                return this.getData.patients(message, 1, (Integer)null);

            default:
                return thirdChoice(message, text);
        }

    }


    public SendMessage thirdChoice(Message message, String text) {

        switch(text) {
            case "1⃣ - bo'lim byudjet\ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 412);
                return this.getData.drugs(message, 1, Type.IMTIYOZ);

            case "1⃣-  bo'lim Muruvvat \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 411);
                return this.getData.drugs(message, 1, Type.MURUVVAT);

            case "2⃣ - bo'lim muruvvat \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 421);
                return this.getData.drugs(message, 2, Type.MURUVVAT);

            case "2⃣ - bo'lim byudjet \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 422);
                return this.getData.drugs(message, 2, Type.IMTIYOZ);

            case "Muruvvat \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 431);
                return this.getData.drugs(message, 0, Type.MURUVVAT);

            case "Byudjet\ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 432);
                return this.getData.drugs(message, 0, Type.IMTIYOZ);

            case "1⃣ - bo'lim pullik \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 413);
                return this.getData.drugs(message, 1, Type.PULLIK);

            case "2⃣ - bo'lim pullik \ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 423);
                return this.getData.drugs(message, 2, Type.PULLIK);

            case "Pullik\ud83d\udc8a":
                MainBot.user.put(message.getChatId(), 433);
                return this.getData.drugs(message, 0, Type.PULLIK);

            default:
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setText("Noto'g'ri komanda berildi");
                return sendMessage;
        }
    }


    public SendMessage back(Message message) {
        Integer position = MainBot.user.get(message.getChatId());
        switch(position) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 11:
            case 12:
            case 13:
            case 31:
            case 32:
            case 33:
            case 34:
                return this.start(message);
            case 41:
            case 42:
            case 43:
            case 411:
            case 412:
            case 413:
            case 421:
            case 422:
            case 423:
            case 431:
            case 432:
            case 433:
                MainBot.user.put(message.getChatId(), 0);
                return this.firstChoice(message, "dorixona \ud83d\udc8a \ud83d\udc89");
            default:
                return this.start(message);
        }
    }

    public SendMessage choiceData(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Faylni yuklang");
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        switch(text) {
            case "TVKD hizmat narxlari \ud83d\ude91":
                MainBot.admin.put(message.getChatId(), 12);
                return sendMessage;

            case "Oziq ovqat ombori \ud83e\uddc5\ud83e\udd54\ud83e\udd6c":
                MainBot.admin.put(message.getChatId(), 13);
                return sendMessage;

            case "dorixona \ud83d\udc8a \ud83d\udc89":
                MainBot.admin.put(message.getChatId(), 14);
                return sendMessage;

            case "Davolanayotgan bemorlar ro'yhxati \ud83d\udecf":
                MainBot.admin.put(message.getChatId(), 11);
                return sendMessage;

            default:
                MainBot.admin.clear();
                return this.start(message);
        }

    }


    public HashSet<Long> getAllChatId() {
        List<Long> chatIds = userRepository.getAllChatIds();
        return new HashSet(chatIds);
    }
}
