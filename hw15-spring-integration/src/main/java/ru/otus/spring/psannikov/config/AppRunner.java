package ru.otus.spring.psannikov.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.services.MemoService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private final MemoService memoService;

    @Override
    public void run(String... args) {
        memoService.startGenerateMemoLoop();
    }
}
