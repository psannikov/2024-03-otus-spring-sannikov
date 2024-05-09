package ru.otus.spring.psannikov.spring.boot.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.boot.shell.config.LocaleConfig;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final LocaleConfig localeConfig;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeConfig.getLocale());
    }
}
