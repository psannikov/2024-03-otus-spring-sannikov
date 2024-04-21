package ru.otus.spring.psannikov.spring.boot.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "test")
public class AppProperties implements TestConfig, TestFileNameProvider, LocaleConfig {

    @Getter
    private final int rightAnswersCountToPass;

    @Getter
    private Locale locale;

    private final Map<String, String> fileNameByLocaleTag;

    @ConstructorBinding
    public AppProperties(int rightAnswersCountToPass, Locale locale, Map<String, String> fileNameByLocaleTag) {
        this.rightAnswersCountToPass = rightAnswersCountToPass;
        this.locale = locale;
        this.fileNameByLocaleTag = fileNameByLocaleTag;
    }

//    public void setLocale(String locale) {
//        this.locale = Locale.forLanguageTag(locale);
//    }

    @Override
    public String getTestFileName() {
        System.out.println(locale.getDisplayLanguage());
        System.out.println(fileNameByLocaleTag.get(locale.getDisplayLanguage()));
        return fileNameByLocaleTag.get(locale.getDisplayLanguage());
    }
}
