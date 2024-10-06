package ru.otus.spring.psannikov.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.domain.Memo;
import ru.otus.spring.psannikov.domain.Task;
import ru.otus.spring.psannikov.domain.User;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final TaskGateway taskTracker;

    @Override
    public void startGenerateMemoLoop() {
        for (int i = 0; i < 5; i++) {
            Memo memo = generateMemo();
            log.info("New memo: {}", memo.getTitle());
            Task task = taskTracker.process(memo);
            delay();
        }
    }

    private Memo generateMemo() {
        return Memo
                .builder()
                .title("Memo title: " + RandomUtils.nextInt())
                .fullText("Full text: " + RandomUtils.nextInt())
                .author(new User("Petr", "Petrov"))
                .date(LocalDateTime.now())
                .build();
    }

    private void delay() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
