package ru.otus.spring.psannikov.front.end.lite.task.tracker.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.otus.spring.psannikov.front.end.lite.task.tracker.services.TelegramBotService;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final TelegramBotService botService;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(botService);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
