package ru.otus.spring.psannikov.spring.boot.shell.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.spring.boot.shell.config.AppProperties;
import ru.otus.spring.psannikov.spring.boot.shell.config.TestFileNameProvider;
import ru.otus.spring.psannikov.spring.boot.shell.dao.dto.QuestionDto;
import ru.otus.spring.psannikov.spring.boot.shell.domain.Question;
import ru.otus.spring.psannikov.spring.boot.shell.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {

    @Autowired
    private final AppProperties appProperties;

    private final TestFileNameProvider fileNameProvider;

    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new QuestionReadException("file not found! " + fileName, new Throwable());
        } else {
            return inputStream;
        }

    }

    @Override
    public List<Question> findAll() {
        String fileName = fileNameProvider.getTestFileName();
        InputStream is = getFileFromResourceAsStream(fileName);
        CsvToBean<QuestionDto> csvToBean =
                new CsvToBeanBuilder<QuestionDto>(new InputStreamReader(is, StandardCharsets.UTF_8))
                        .withType(QuestionDto.class)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();
        List<QuestionDto> questionDtos = csvToBean.parse();
        return questionDtos.stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }
}
