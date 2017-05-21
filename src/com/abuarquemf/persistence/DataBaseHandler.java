package com.abuarquemf.persistence;

import com.abuarquemf.entities.Avatar;
import com.abuarquemf.entities.Emotion;
import com.abuarquemf.entities.EmotionType;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Modified by animal505 on 18/05/17.
 *
 * To get data from database :
 * user : nees_user
 * pass : nees_2017_ontology
 */
public class DataBaseHandler {
    private MongoClient mongoClient;
    private DB db;
    private DBCollection collection;

    private static final String USER = "nees_user";
    private static final String PASS = "nees_2017_ontology";
    private static final String URL_DATABASE = "mongodb://" + USER + ":" + PASS + "@ds023000.mlab.com:23000/ontology_frontend";

    /*
    static {
        try {
            mongoClient = new MongoClient(new MongoClientURI(URL_DATABASE));
        } catch(Exception e) {
            e.printStackTrace();
        }
    } */

    /**
     * Implementing Singleton design pattern. Such design pattern guarantee a single instance in the whole system
     */
    private static DataBaseHandler instance = null;
    private List<Avatar> avatars;

    private DataBaseHandler() {
        //avatars = new ArrayList<>();
        try {
            mongoClient = new MongoClient(new MongoClientURI(URL_DATABASE));
        } catch(Exception e) {
            e.printStackTrace();
        }
        db = mongoClient.getDB("ontology_frontend");
        collection = db.getCollection("hello_test");
        //doBootStrap();
    }


    public void saveAvatar(Avatar avatar) {
        Gson gson = new Gson();
        //parsing customer to json and them for an BasicDBObject supported by mongoDB
        BasicDBObject basicDBObject = (BasicDBObject) JSON.parse(gson.toJson(avatar));
        //inserting
        collection.insert(basicDBObject);
    }

    public void saveAvatars(List<Avatar> customers) {
        Gson gson = new Gson();
        for(Avatar avatar : customers) {
            BasicDBObject basicDBObject = (BasicDBObject) JSON.parse(gson.toJson(avatar));
            collection.insert(basicDBObject);
        }
    }

    public Avatar findAvatar(String name) {
        BasicDBObject obj = new BasicDBObject();
        //specifying that the object that i am looking for
        //HAS A NAME CALLED name
        obj.put("name", name);
        //now searching for this object
        DBCursor cursor = collection.find(obj);
        Avatar avatar = null;
        try {
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                avatar = (new Gson()).fromJson(dbObject.toString(), Avatar.class);
            }
        } finally {
            cursor.close();
        }
        return avatar;
    }

    public List<Avatar> getAvatars() {
        DBCursor cursor = collection.find();
        List<Avatar> avatars = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            Avatar x = (new Gson()).fromJson(dbObject.toString(), Avatar.class);
            avatars.add(x);
        }
        return avatars;
    }

    public static DataBaseHandler getInstance() {
        if (instance == null)
            instance = new DataBaseHandler();
        return instance;
    }

    /*
    public void addAvatar(Avatar a) {
        avatars.add(a);
    }

    public void removeAvatar(Avatar a) {
        avatars.remove(a);
    }

    public List<Avatar> getAvatars() {
        return avatars;
    }

    public Avatar findAvatar(String name) {
        for (Avatar element : avatars) {
            if(element.getName().equals(name))
                return element;
        }
        return null;

    } */

    //get files from MongoDB
    private void doBootStrap() {
        List<Emotion> emotions = new ArrayList<>();
        emotions.add(new Emotion(EmotionType.OPENED));
        emotions.add(new Emotion(EmotionType.SADNESS));
        emotions.add(new Emotion(EmotionType.SURPRISE));
        emotions.add(new Emotion(EmotionType.ANGER));
        emotions.add(new Emotion(EmotionType.DISGUST));
        emotions.add(new Emotion(EmotionType.FEAR));
        emotions.add(new Emotion(EmotionType.JOY));
        emotions.add(new Emotion(EmotionType.NEUTRAL));

        Avatar a = new Avatar("adriana_marangoni", emotions);
        Avatar b = new Avatar("anna_costa", emotions);
        avatars.add(a);
        avatars.add(b);
    }
}