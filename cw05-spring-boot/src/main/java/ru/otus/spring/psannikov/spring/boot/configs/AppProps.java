package ru.otus.spring.psannikov.spring.boot.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
public class AppProps {
    private final String message;
    private final Locale locale;
    private final List<String> data;
    private final Map<String, Integer> mapa;

    @ConstructorBinding
    public AppProps(String message, Locale locale, List<String> data, Map<String, Integer> mapa) {
        this.message = message;
        this.locale = locale;
        this.data = data;
        this.mapa = mapa;
    }
    public String getMessage() {
        return message;
    }
    public Locale getLocale() {
        return locale;
    }

    public List<String> getData() {
        return data;
    }

    public Map<String, Integer> getMapa() {
        return mapa;
    }
}
