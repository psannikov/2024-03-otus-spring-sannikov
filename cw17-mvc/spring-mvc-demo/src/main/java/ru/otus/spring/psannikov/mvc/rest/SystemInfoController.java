package ru.otus.spring.psannikov.mvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.mvc.domain.SystemInfo;

@RestController
public class SystemInfoController {

    @GetMapping("/server/system/info")
    public SystemInfo getServerSystemInfo(SystemInfo systemInfo) {
        return systemInfo;
    }
}
