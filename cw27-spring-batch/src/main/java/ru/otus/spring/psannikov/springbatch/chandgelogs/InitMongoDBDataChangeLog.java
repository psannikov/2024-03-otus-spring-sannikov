package ru.otus.spring.psannikov.springbatch.chandgelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.psannikov.springbatch.model.Person;

@ChangeLog(order = "000")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initPersons", author = "stvort", runAlways = true)
    public void initPersons(MongockTemplate template){
        System.out.println("Заливка в монгу начата");
        template.save(new Person("Джон", 21));
        template.save(new Person("Игорь", 32));
        template.save(new Person("Дмитрий", 52));
        template.save(new Person("Михаил", 22));
        template.save(new Person("Герман", 33));
        template.save(new Person("Джон", 21));
        template.save(new Person("Игорь", 32));
        template.save(new Person("Дмитрий", 52));
        template.save(new Person("Михаил", 22));
        template.save(new Person("Герман", 33));
        template.save(new Person("Джон", 21));
        template.save(new Person("Игорь", 32));
        template.save(new Person("Дмитрий", 52));
        template.save(new Person("Михаил", 22));
        template.save(new Person("Герман", 33));
        template.save(new Person("Джон", 21));
        template.save(new Person("Игорь", 32));
        template.save(new Person("Дмитрий", 52));
        template.save(new Person("Михаил", 22));
        template.save(new Person("Герман", 33));
        template.save(new Person("Джон", 21));
        template.save(new Person("Игорь", 32));
        template.save(new Person("Дмитрий", 52));
        template.save(new Person("Михаил", 22));
        template.save(new Person("Герман", 33));
    }
}
