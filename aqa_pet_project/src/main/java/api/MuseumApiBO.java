package api;

import api.templates.MuseumTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.AllureUIListener;
import mongo.MongoDb;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class MuseumApiBO {

    ObjectMapper objectMapper = new ObjectMapper();
    public String login(){
        String loginUrl = "http://localhost:5000/api/login";

        String body = "{\"login\":\"admin\", \"password\":\"admin\"}";

        Response loginResponse = given()
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(loginUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        String token = loginResponse.jsonPath().getString("token");
        Assert.assertNotNull(token);
        return token;
    }


    public MuseumTemplate createMuseum(String token)  {

        String createMuseumUrl = "http://localhost:5000/api/admin/addMuseum";
        AllureUIListener.attachAPIRequest(createMuseumUrl);
        File photo = new File(getClass().getClassLoader().getResource("images/Landscape-Color.jpg").getFile());

        Response createMuseumResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .multiPart("ukrTitle", "Some museum")
                .multiPart("ukrWorkingHours", "Some ukr hrs")
                .multiPart("ukrAddress", "Some address")
                .multiPart("engTitle", "Some title")
                .multiPart("engWorkingHours", "Some eng hrs")
                .multiPart("engAddress", "Some end address")
                .multiPart("link", "https://www.website.com/")
                .multiPart("phone", "+380978034165")
                .multiPart("email", "someemail@gmail..com")
                .multiPart("photo", photo, "image/jpeg")
                .when()
                .post(createMuseumUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        AllureUIListener.attachAPIResponse(createMuseumResponse);



        MuseumTemplate museumTemplate = createMuseumResponse.jsonPath().getObject("museum", MuseumTemplate.class);
        System.out.println("museum template form API response: " + museumTemplate);

        return museumTemplate;

    }
    public MuseumTemplate updateMuseum(String token, String museumId)  {

        String updateMuseumUrl = "http://localhost:5000/api/admin/editMuseum";
        File photo = new File(getClass().getClassLoader().getResource("images/Landscape-Color.jpg").getFile());

        AllureUIListener.attachAPIRequest(updateMuseumUrl);

        Response updateMuseumResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .multiPart("ukrTitle", "Some updated museum")
                .multiPart("ukrWorkingHours", "Some updated ukr hrs")
                .multiPart("ukrAddress", "Some address")
                .multiPart("engTitle", "Some title")
                .multiPart("engWorkingHours", "Some eng hrs")
                .multiPart("engAddress", "Some end address")
                .multiPart("link", "https://www.website.com/")
                .multiPart("phone", "+380978034165")
                .multiPart("email", "someemail@gmail..com")
                .multiPart("photo", photo, "image/jpeg")
                .multiPart("museumId", museumId)
                .when()
                .patch(updateMuseumUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        AllureUIListener.attachAPIResponse(updateMuseumResponse);

        MuseumTemplate museumTemplate = updateMuseumResponse.jsonPath().getObject("museum", MuseumTemplate.class);
        System.out.println("Museum template form API response: " + museumTemplate);

        return museumTemplate;

    }

    public MuseumTemplate deleteMuseum(String token, String museumId){
        String deleteMuseumUrl = "http://localhost:5000/api/admin/deleteMuseum";
        String body = "{\"museumId\":\""+museumId+"\"}";

        AllureUIListener.attachAPIRequest(deleteMuseumUrl);


        Response deleteMuseumResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .delete(deleteMuseumUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        AllureUIListener.attachAPIResponse(deleteMuseumResponse);

        MuseumTemplate museumTemplate = deleteMuseumResponse.jsonPath().getObject("museum", MuseumTemplate.class);
        System.out.println("Museum template form API response: " + museumTemplate);
        return museumTemplate;

    }

    public MuseumTemplate getMuseumFromDB(String id) {
        MongoDb.init();
        MongoCollection<Document> museumsCollection = MongoDb.getMuseumCollection();

        ObjectId objectId = new ObjectId(id);

        Document museum = museumsCollection.find(Filters.eq("_id", objectId)).first();

        museum.put("_id", museum.getObjectId("_id").toHexString());
        String postJson = museum.toJson();

        MuseumTemplate museumTemplate = null;
        try {
            museumTemplate = objectMapper.readValue(postJson, MuseumTemplate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            MongoDb.closeConnection();
        }
        System.out.println("Post template from database: " + museumTemplate);
        return museumTemplate;
    }

    public boolean isMuseumDeleted(String museumId) {
        try {
            MongoDb.init();
            MongoCollection<Document> museumsCollection = MongoDb.getMuseumCollection();

            ObjectId objectId = new ObjectId(museumId);

            Document post = museumsCollection.find(Filters.eq("_id", objectId)).first();

            return post == null;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ObjectId format for postId: " + museumId);
            return true;
        } finally {
            MongoDb.closeConnection();
        }
    }


}
