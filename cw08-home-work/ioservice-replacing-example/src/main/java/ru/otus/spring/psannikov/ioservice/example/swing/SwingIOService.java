package ru.otus.spring.psannikov.ioservice.example.swing;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.ioservice.example.api.IOService;

@ConditionalOnProperty(name = "use.console", havingValue = "false")
@RequiredArgsConstructor
@Service
public class SwingIOService implements IOService {
    private final MessageSystem ms;

    @SneakyThrows
    @Override
    public void out(String message) {
        ms.putToOutputQueue(message);
    }

    @SneakyThrows
    @Override
    public String readString() {
        return ms.takeFromInputQueue();
    }
}
