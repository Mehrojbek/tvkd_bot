package uz.mehrojbek.apptvkdbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotButton {
    public ReplyKeyboardMarkup startButton() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        markup.setResizeKeyboard(true);
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Davolanayotgan bemorlar ro'yhxati \ud83d\udecf");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("TVKD hizmat narxlari \ud83d\ude91");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Oziq ovqat ombori \ud83e\uddc5\ud83e\udd54\ud83e\udd6c");
        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add("dorixona \ud83d\udc8a \ud83d\udc89");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(keyboardRow4);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup listPatient() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("1⃣- bo'lim bemorlar ro'yhati");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("2⃣- bo'lim bemorlar ro'yhati");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Intensiv palatadagi bemorlar ro'yhati");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup listFood() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Zahira \ud83e\udd54\ud83e\udd55\ud83e\udd6c");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Muruvvat  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Imtiyoz  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c");
        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add("Pullik  \ud83e\uddc5\ud83e\udd54\ud83e\udd6c");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(keyboardRow4);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup medicine() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("1 -bo'lim \ud83d\udc8a");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("2 -bo'lim \ud83d\udc8a");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Ichki dorixona \ud83d\udc8a");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup innerMedicineFirst() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("1⃣-  bo'lim Muruvvat \ud83d\udc8a");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("1⃣ - bo'lim byudjet\ud83d\udc8a");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("1⃣ - bo'lim pullik \ud83d\udc8a");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup innerMedicineSecond() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("2⃣ - bo'lim muruvvat \ud83d\udc8a");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("2⃣ - bo'lim byudjet \ud83d\udc8a");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("2⃣ - bo'lim pullik \ud83d\udc8a");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup innerMedicineThird() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Muruvvat \ud83d\udc8a");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Byudjet\ud83d\udc8a");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Pullik\ud83d\udc8a");
        KeyboardRow control = new KeyboardRow();
        control.add("\ud83d\udd19 Back");
        control.add("\ud83d\udd1d Main Menu");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(control);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public ReplyKeyboardMarkup adminButton() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList();
        markup.setResizeKeyboard(true);
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Davolanayotgan bemorlar ro'yhxati \ud83d\udecf");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("TVKD hizmat narxlari \ud83d\ude91");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Oziq ovqat ombori \ud83e\uddc5\ud83e\udd54\ud83e\udd6c");
        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add("dorixona \ud83d\udc8a \ud83d\udc89");
        KeyboardRow cancel = new KeyboardRow();
        cancel.add("\ud83d\udd19 Bekor qilish");
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);
        keyboardRows.add(keyboardRow4);
        keyboardRows.add(cancel);
        markup.setKeyboard(keyboardRows);
        return markup;
    }

    public InlineKeyboardMarkup davomi(Integer sectionId) {
        InlineKeyboardMarkup davomi = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("davomi");
        button.setCallbackData(String.valueOf(sectionId));
        List<InlineKeyboardButton> row = new ArrayList();
        row.add(button);
        List<List<InlineKeyboardButton>> rowlist = new ArrayList();
        rowlist.add(row);
        davomi.setKeyboard(rowlist);
        return davomi;
    }
}
