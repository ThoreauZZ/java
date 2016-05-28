package com.henry.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by zhaozhou on 16/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  { "classpath:/applicationContext.xml" })
public class MongoAppTest {
    private static Logger log = LoggerFactory.getLogger(MongoAppTest.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void Test1() {
        Person p = new Person("Joe", 34);
        // Insert is used to initially store the object into the database.
        //show collections  person,
        //{ "_id" : ObjectId("5745caf4dbb0066ec070e0d4"), "_class" : "com.henry.mongo.Person", "name" : "Joe", "age" : 34 }
        mongoTemplate.insert(p);
        log.info("Insert: " + p);

        p = mongoTemplate.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        mongoTemplate.updateFirst(query(where("name").is("Joe")),update("age",35), Person.class);
        p = mongoTemplate.findOne(query(where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

//        mongoTemplate.dropCollection(Person.class);

    }

    @Test
    public void test2(){
        Person person = mongoTemplate.findOne(new Query(Criteria.where("name").is("Joe").and("age").is(35)), Person.class);
        log.debug("find by Criterra,{}",person);

        Query query = new Query(Criteria.where("name").is("Joe").and("age").is(35));
        Update update = new Update().inc("age", 2);
        Person p = mongoTemplate.findAndModify(query, update, Person.class); // return's old person object

        // return's new person object
        p = mongoTemplate.findAndModify(query, update,new FindAndModifyOptions().returnNew(true),Person.class);


    }


}

