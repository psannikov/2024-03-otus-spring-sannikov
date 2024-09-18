package ru.otus.spring.psannikov.batch.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class DatabaseInit {

    @ChangeSet(order = "001", id = "dropDb", author = "psannikov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }
}
