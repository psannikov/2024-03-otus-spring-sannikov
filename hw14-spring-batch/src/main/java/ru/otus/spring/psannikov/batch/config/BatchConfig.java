package ru.otus.spring.psannikov.batch.config;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.spring.psannikov.batch.models.jpa.Book;
import ru.otus.spring.psannikov.batch.models.mongo.MongoBook;

@Configuration
public class BatchConfig {

    private final Logger logger = LoggerFactory.getLogger("Log");

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public static final String IMPORT_USER_JOB_NAME = "importUserJob";
    private static final int CHUNK_SIZE = 5;

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
        return (ItemProcessor<Book, MongoBook>) book -> MongoBook.convert(book);
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
        return new JobBuilder(IMPORT_USER_JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(ItemReader reader, ItemWriter writer, ItemProcessor processor) {

        return new StepBuilder("transformBooksStep", jobRepository)
                .<Book, MongoBook>chunk(CHUNK_SIZE, platformTransactionManager)
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
