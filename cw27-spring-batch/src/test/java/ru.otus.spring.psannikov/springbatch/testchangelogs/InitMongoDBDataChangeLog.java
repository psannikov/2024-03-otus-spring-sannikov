package ru.otus.spring.psannikov.springbatch.testchangelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.psannikov.springbatch.model.Person;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPersons", author = "stvort", runAlways = true)
    public void initPersons(MongockTemplate template){
        template.save(new Person("Тестовый Джон", 21));
        template.save(new Person("Тестовый Игорь", 32));
        template.save(new Person("Тестовый Дмитрий", 52));
        template.save(new Person("Тестовый Михаил", 22));
        template.save(new Person("Тестовый Герман", 33));
        template.save(new Person("Тестовый Джон", 21));
        template.save(new Person("Тестовый Игорь", 32));
        template.save(new Person("Тестовый Дмитрий", 52));
        template.save(new Person("Тестовый Михаил", 22));
        template.save(new Person("Тестовый Герман", 33));
        template.save(new Person("Тестовый Джон", 21));
        template.save(new Person("Тестовый Игорь", 32));
        template.save(new Person("Тестовый Дмитрий", 52));
        template.save(new Person("Тестовый Михаил", 22));
        template.save(new Person("Тестовый Герман", 33));
        template.save(new Person("Тестовый Джон", 21));
        template.save(new Person("Тестовый Игорь", 32));
        template.save(new Person("Тестовый Дмитрий", 52));
        template.save(new Person("Тестовый Михаил", 22));
        template.save(new Person("Тестовый Герман", 33));
        template.save(new Person("Тестовый Джон", 21));
        template.save(new Person("Тестовый Игорь", 32));
        template.save(new Person("Тестовый Дмитрий", 52));
        template.save(new Person("Тестовый Михаил", 22));
        template.save(new Person("Тестовый Герман", 33));
    }
}
