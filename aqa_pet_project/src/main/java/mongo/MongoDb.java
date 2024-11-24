package mongo;

import com.mongodb.client.*;
import org.bson.Document;
import util.PropertyReader;

public class MongoDb {
    private static MongoDatabase database;
    private static MongoClient mongoClient;


    public static void init() {
        if(database == null) {
            PropertyReader.init();
            String username = PropertyReader.getProperty("mongoDbUser");
            String password = PropertyReader.getProperty("mongoDbPassword");
            String databaseName = PropertyReader.getProperty("mongoDbDatabase");
            String clusterName = PropertyReader.getProperty("mongoDbClusterName");

            String uri = "mongodb+srv://" + username + ":" + password + "@" + clusterName + ".mongodb.net/?retryWrites=true&w=majority&appName=Sandbox";

            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase(databaseName);
        }
    }

    public static MongoCollection<Document> getPostsCollection(){
        String collectionName = "contents";
        if(database == null) init();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
        }
    }

}
