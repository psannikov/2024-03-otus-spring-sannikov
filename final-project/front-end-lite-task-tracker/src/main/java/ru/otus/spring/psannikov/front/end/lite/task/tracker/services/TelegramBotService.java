package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskShortDto;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    private final String botName = "LiteTaskTracker_bot";

    private final String token = "8037399289:AAF6XnN8NJd4S_b3BwoIvsyLfQcKfgVFstI";

    private final DataService dataService;

    private final ExcelExportService excelExportService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/tasks" -> tasksCommandReceived(chatId);
                case "/report" -> reportCommandReceived(chatId);
                default -> sendMessage(chatId, "Данная команда не поддерживается, список доступных команд /start");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = "Список доступных команд:\n" +
                "/tasks - список всех задач\n" +
                "/report - подготовка отчета, будет запрошен вариант доставки";
        sendMessage(chatId, answer);
    }

    private void tasksCommandReceived(long chatId) {
        var tasks = dataService.getTasksInWork();
        String tasksString = "";
        for (TaskShortDto task : tasks) {
            tasksString = tasksString + task.getId() + "|" + task.getTitle() + "|" + "<a href=\"http://localhost:8080/api/v1/task/1\">Детали</a>\n";
        }
        var answer = "Список всех задач в работе:\n" +
                "<b>ID | Title | Detail </b>\n" + tasksString;
        sendMessage(chatId, answer);
    }

    private void reportCommandReceived(long chatId) {
        var tasks = dataService.getTasksInWork();
        String tasksString = "";
        for (TaskShortDto task : tasks) {
            tasksString = tasksString + task.getId() + "|" + task.getTitle() + "|" + task.getTaskDescription() + "\n";
        }
        var answer = "Список всех задач в работе:\n" +
                "ID | Title | Description\n" + tasksString;
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.enableHtml(true);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
