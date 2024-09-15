package ru.otus.spring.psannikov.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.psannikov.batch.models.mongo.MongoAuthor;
import ru.otus.spring.psannikov.batch.models.mongo.MongoBook;
import ru.otus.spring.psannikov.batch.models.mongo.MongoComment;
import ru.otus.spring.psannikov.batch.models.mongo.MongoGenre;
import ru.otus.spring.psannikov.batch.models.postgres.Book;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    private final Logger logger = LoggerFactory.getLogger("Log");
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
    }

    @Bean
    public ItemReader<Book> reader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Book>()
                .name("BookReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select b from Book b")
                .build();
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<Book, MongoBook>) book -> {
            String title = book.getTitle();
            MongoAuthor author = MongoAuthor.convert(book.getAuthor());
            MongoGenre genre = MongoGenre.convert(book.getGenre());
            List<MongoComment> comments = book.getComments().stream().map(MongoComment::convert).collect(Collectors.toList());

            return new MongoBook(title, author, genre, comments);

        };
    }

    @Bean
    public ItemWriter<MongoBook> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<MongoBook>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("---------------------->Start job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("<----------------------End job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(ItemReader reader, ItemWriter writer, ItemProcessor processor) {
        return stepBuilderFactory.get("step1")
                .chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ItemReadListener<Book>() {
                    public void beforeRead() {
                        logger.info("Start read");
                    }

                    public void afterRead(Book book) {
                        logger.info("Book " + book.getTitle());
                    }

                    public void onReadError(Exception e) {
                        logger.info("Read error");
                    }
                })
                .build();
    }

}
