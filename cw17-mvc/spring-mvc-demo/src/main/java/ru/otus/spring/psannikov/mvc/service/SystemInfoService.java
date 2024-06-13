package ru.otus.spring.psannikov.mvc.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.mvc.domain.SystemInfo;

@Service
public class SystemInfoService {

    public SystemInfo getSystemInfo(){
        String osName = System.getProperty("os.name");
        String timeZone = System.getProperty("user.timezone");
        String osArch = System.getProperty("os.arch");
        int processorsCount = Runtime.getRuntime().availableProcessors();
        return new SystemInfo(osName, timeZone, osArch, processorsCount);

    }
}
