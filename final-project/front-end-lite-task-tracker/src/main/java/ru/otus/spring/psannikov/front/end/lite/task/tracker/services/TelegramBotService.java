package ru.otus.spring.psannikov.front.end.lite.task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.converter.TaskConverter;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.dtos.TaskDto;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${BOT_TOKEN}")
    private String token;

    private final DataService dataService;

    private final ExcelExportService excelExportService;

    private final TaskConverter taskConverter;

    private final Map<Long, Boolean> chatState = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (chatState.containsKey(chatId) && chatState.get(chatId)) {
                handleTaskNumberInput(chatId, messageText);
            } else {
                switch (messageText) {
                    case "/start" -> startCommandReceived(chatId);
                    case "/tasks" -> tasksCommandReceived(chatId);
                    case "/task" -> taskComandReceived(chatId);
                    case "/report" -> reportCommandReceived(chatId);
//                    case "/test" -> test(chatId);
                    default -> sendMessage(chatId, "Данная команда не поддерживается, список доступных команд /start");
                }
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

    private void startCommandReceived(long chatId) {
        String answer = "Список доступных команд:\n" +
                "/tasks - список всех задач\n" +
                "/task - детали задачи, будет запрошен номер\n" +
                "/report - подготовка отчета, будет запрошен вариант доставки\n" +
                "/test - разработка методов";
        sendMessage(chatId, answer);
    }

    private void tasksCommandReceived(long chatId) {
        var tasks = dataService.getTasksInWork();
        String tasksString = "";
        for (TaskDto task : tasks) {
            tasksString = tasksString + task.getId() + "|" + task.getTitle() + "|" + task.getDescription() + "\n";
        }
        var answer = "Список всех задач в работе:\n" +
                "<b>ID | Title | Detail </b>\n" + tasksString;
        sendMessage(chatId, answer);
    }

    private void reportCommandReceived(long chatId) {
        var tasks = dataService.getAllTasks();
        File xlsxFile = excelExportService.exportTasksToExcel(tasks);
        SendDocument sendDocumentRequest = new SendDocument();
        sendDocumentRequest.setChatId(String.valueOf(chatId));
        sendDocumentRequest.setDocument(new org.telegram.telegrambots.meta.api.objects.InputFile(xlsxFile));
        try {
            execute(sendDocumentRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        var answer = "Список всех задач подготовлен";
        sendMessage(chatId, answer);
    }

    private void taskComandReceived(long chatId) {
        sendMessage(chatId, "Введите номер задачи (от 1 до 1000):");
        chatState.put(chatId, true);
    }

    private void handleTaskNumberInput(long chatId, String messageText) {
        try {
            int taskId = Integer.parseInt(messageText);
            if (taskId >= 1 && taskId <= 1000) {
                var res = dataService.getTasksById(taskId);
                if (res.isPresent()) {
                    var answer = taskConverter.taskToString(res.get());
                    sendMessage(chatId, answer);
                } else {
                    sendMessage(chatId, "Задача с таким номером не найдена.");
                }
            } else {
                sendMessage(chatId, "Пожалуйста, введите число от 1 до 1000.");
            }
        } catch (NumberFormatException e) {
            sendMessage(chatId, "Некорректный ввод. Введите число от 1 до 1000.");
        } finally {
            chatState.put(chatId, false);
        }
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
