package com.notificationService.serviceImpl;

import com.notificationService.entities.NotificationDatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Transactional
public class SequenceGeneratorSerivce {

    private static MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorSerivce(MongoOperations mongoOperations) {
        SequenceGeneratorSerivce.mongoOperations = mongoOperations;
    }

    public static long generateSequence(String seqName) {

        NotificationDatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true), NotificationDatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
