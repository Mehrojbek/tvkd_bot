package uz.mehrojbek.apptvkdbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.mehrojbek.apptvkdbot.bot.MainBot;
import uz.mehrojbek.apptvkdbot.entity.Patient;
import uz.mehrojbek.apptvkdbot.entity.Price;
import uz.mehrojbek.apptvkdbot.entity.enums.Type;
import uz.mehrojbek.apptvkdbot.repository.PatientRepository;
import uz.mehrojbek.apptvkdbot.repository.PriceRepository;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

@Component
public class GetData {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    BotButton botButton;
    @Autowired
    PatientRepository patientRepository;

    public SendMessage price(Message message) {
        List<Price> prices = this.priceRepository.findAll();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        String text = "";

        Price price;
        DecimalFormat format;
        for(Iterator var = prices.iterator(); var.hasNext(); text = text + "<b>" + price.getSection() + "</b>     <b><i>" +
                price.getServiceName() + "</i></b>   O`zb. fuqarolariga -- " + format.format(price.getToUzb()) + "   Chet el fuqarolariga -- " +
                format.format(price.getToOther()) + "\n\n") {
            price = (Price)var.next();
            format = new DecimalFormat();
            format.setMaximumFractionDigits(15);
        }

        sendMessage.setText(text.equals("")?"Bo'sh":text);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(this.botButton.startButton());
        return sendMessage;
    }


    public SendMessage patients(Message message, Integer sectionId, Integer id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        String text = "";
        sendMessage.enableHtml(true);
        text = text + "<b>Bo'lim   " + "palata   " + "F.I.SH   " + "kelgan   " + "ketadi   " + "pullik yoki imtiyoz   " + "qavat</b>\n\n";
        List patients;
        if (id == null) {
            patients = this.patientRepository.findAllBySectionNumber(sectionId);
        } else {
            patients = this.patientRepository.findAllBySectionNumberAndIdIsGreaterThan(sectionId, id);
        }

        int counter = 0;
        Patient patient;
        String bolim;
        String palata;
        String inDate;
        String outDate;
        String isPaid;
        if (!patients.isEmpty()) {
            for(Iterator var8 = patients.iterator(); var8.hasNext(); text = text + counter + ") <b>" + bolim + "</b><i>" + palata + "-palata</i><b><i>  " + patient.getFullName() + "</i></b>  " + inDate + "   " + outDate + "<b>  " + isPaid + "</b>  <i>" + patient.getFloor() + "-qavat</i>\n\n") {
                patient = (Patient)var8.next();
                int length = text.length();
                if (length >= 3900) {
                    MainBot.patientSize.put(message.getChatId(), patient.getId() - 1);
                    sendMessage.setText(text);
                    sendMessage.enableHtml(true);
                    sendMessage.setReplyMarkup(this.botButton.davomi(sectionId));
                    return sendMessage;
                }

                bolim = "";
                palata = "";
                if (patient.getSectionNumber() == 0) {
                    bolim = "intensiv bo'lim   ";
                } else {
                    bolim = patient.getSectionNumber() + "-bo'lim   ";
                }

                if (patient.getWardNumber() == 0) {
                    palata = "intensiv";
                } else {
                    palata = patient.getWardNumber().toString();
                }

                if (patient.getFullName().equals("Bo'sh")) {
                    inDate = "---";
                    outDate = "---";
                    isPaid = "---";
                } else {
                    inDate = patient.getInDate();
                    outDate = patient.getOutDate();
                    isPaid = patient.getIsPaid();
                }

                ++counter;
            }
        }

        sendMessage.setText(text);
        sendMessage.setReplyMarkup(this.botButton.listPatient());
        return sendMessage;
    }

    public SendMessage reserve(Message message) {
        return null;
    }

    public SendMessage grace(Message message) {
        return null;
    }

    public SendMessage privilege(Message message) {
        return null;
    }

    public SendMessage paid(Message message) {
        return null;
    }

    public SendMessage drugs(Message message, Integer sectionId, Type type) {
        return null;
    }

}
