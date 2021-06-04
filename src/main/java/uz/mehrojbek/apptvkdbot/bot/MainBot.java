package uz.mehrojbek.apptvkdbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.mehrojbek.apptvkdbot.entity.enums.Status;
import uz.mehrojbek.apptvkdbot.service.BotButton;
import uz.mehrojbek.apptvkdbot.service.BotService;
import uz.mehrojbek.apptvkdbot.service.GetData;
import uz.mehrojbek.apptvkdbot.service.SaveService;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class MainBot extends TelegramLongPollingBot {

    @Autowired
    SaveService saveService;
    @Autowired
    BotService botService;
    @Autowired
    BotButton botButton;
    @Autowired
    GetData getData;

    private final String PASSWORD = "1234";
    private final String ENTER_PASSWORD = "12345";
    public static HashMap<Long, Integer> user = new HashMap();
    public static HashMap<Long, Integer> patientSize = new HashMap();
    public static HashMap<Long, Integer> admin = new HashMap();
    public static HashSet<Long> systemUsers = new HashSet();
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String username;



    @Override
    public String getBotUsername() {
        return this.username;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    public void onUpdateReceived(Update update) {
        try {
            if (systemUsers.isEmpty()) {
                systemUsers.addAll(botService.getAllChatId());
            }

            SendMessage sendMessage;
            String text;
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                Message message = callbackQuery.getMessage();
                text = callbackQuery.getData();
                Integer sectionId = Integer.valueOf(text);
                Integer id = patientSize.get(message.getChatId());
                sendMessage = getData.patients(message, sectionId, id);
                execute(sendMessage);
            } else {
                if (update.hasMessage()) {
                    Message message = update.getMessage();
                    Long chatId = message.getChatId();
                    if (message.hasText()) {
                        text = message.getText();
                        SendMessage choice;
                        if (text.equals(ENTER_PASSWORD)) {
                            botService.saveUser(message, Status.MEMBER);
                            choice = botService.start(message);
                            execute(choice);
                            systemUsers.clear();
                            return;
                        }

                        if (!systemUsers.contains(chatId)) {
                            execute(botService.enterPassword(message));
                            return;
                        }

                        if (text.equals("/start")) {
                            choice = botService.enterPassword(message);
                            execute(choice);
                            return;
                        }

                        if (text.equals("/excel")) {
                            admin.clear();
                            choice = new SendMessage();
                            choice.setText("parolni kiriting:");
                            choice.setChatId(String.valueOf(message.getChatId()));
                            execute(choice);
                            admin.put(message.getChatId(), 0);
                            return;
                        }

                        if (text.equals(PASSWORD) && admin.containsKey(message.getChatId())) {
                            admin.put(message.getChatId(), 1);
                            choice = new SendMessage();
                            choice.setChatId(String.valueOf(message.getChatId()));
                            choice.setText("Qanday ma'lumot yuklamoqchisiz");
                            choice.setReplyMarkup(botButton.adminButton());
                            execute(choice);
                            return;
                        }

                        if (admin.containsKey(message.getChatId()) && (Integer) admin.get(message.getChatId()) == 1) {
                            choice = botService.choiceData(message, text);
                            execute(choice);
                            return;
                        }

                        choice = botService.firstChoice(message, text);
                        execute(choice);
                        return;
                    }

                    if (message.hasDocument() && admin.containsKey(message.getChatId())) {
                        Document document = message.getDocument();
                        GetFile getFile = new GetFile(document.getFileId());

                        try {
                            org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
                            File originalFile = downloadFile(file);
                            File downloads = new File("downloads/" + document.getFileName());
                            FileCopyUtils.copy(originalFile, downloads);
                            Integer choice = admin.get(message.getChatId());
                            admin.clear();
                            switch (choice) {
                                case 11:
                                    SendMessage patientMessage = saveService.savePatient(message, document.getFileName());
                                    execute(patientMessage);
                                    downloads.deleteOnExit();
                                    return;
                                case 12:
                                    SendMessage priceMessage = saveService.savePrice(message, document.getFileName());
                                    execute(priceMessage);
                                    downloads.deleteOnExit();
                                    return;
                                case 13:
                                    SendMessage foodMessage = saveService.saveFood(message);
                                    execute(foodMessage);
                                    downloads.deleteOnExit();
                                    return;
                                case 14:
                                    SendMessage drugsMessage = saveService.saveDrugs(message);
                                    execute(drugsMessage);
                                    downloads.deleteOnExit();
                                    return;
                                default:
                                    sendMessage = new SendMessage();
                                    sendMessage.setText("Xatolik");
                                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                                    execute(sendMessage);
                            }
                        } catch (Exception e) {
                            sendMessage = new SendMessage();
                            sendMessage.setText("Fayl yuklashda xatolik yuz berdi");
                            sendMessage.setChatId(String.valueOf(message.getChatId()));
                            execute(sendMessage);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
