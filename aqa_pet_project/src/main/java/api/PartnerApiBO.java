package api;

import api.templates.PartnerTemplate;
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

public class PartnerApiBO {

    ObjectMapper objectMapper = new ObjectMapper();
    public String login(){
        String loginUrl = "http://localhost:5000/api/login";

        String body = "{\"login\":\"admin\", \"password\":\"admin\"}";

        AllureUIListener.attachAPIRequest(loginUrl);


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

        AllureUIListener.attachAPIResponse(loginResponse);

        String token = loginResponse.jsonPath().getString("token");
        Assert.assertNotNull(token);
        return token;
    }

    public PartnerTemplate createPartner(String token)  {

        String createPartnerUrl = "http://localhost:5000/api/admin/addPartner";
        File photo = new File(getClass().getClassLoader().getResource("images/partner1.jpg").getFile());

        AllureUIListener.attachAPIRequest(createPartnerUrl);

        Response createPartnerResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .multiPart("name", "Some partner")
                .multiPart("link", "https://www.google.com/")
                .multiPart("logo", photo, "image/jpeg")
                .when()
                .post(createPartnerUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        AllureUIListener.attachAPIResponse(createPartnerResponse);



        PartnerTemplate partnerTemplate = createPartnerResponse.jsonPath().getObject("partner", PartnerTemplate.class);
        System.out.println("Partner template form API response: " + partnerTemplate);

        return partnerTemplate;

    }

    public PartnerTemplate deletePartner(String token, String partnerId){
        String deletePartnerUrl = "http://localhost:5000/api/admin/deletePartner";
        String body = "{\"partnerId\":\""+partnerId+"\"}";

        AllureUIListener.attachAPIRequest(deletePartnerUrl);


        Response deletePartnerResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .delete(deletePartnerUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        AllureUIListener.attachAPIResponse(deletePartnerResponse);

        PartnerTemplate partnerTemplate = deletePartnerResponse.jsonPath().getObject("partner", PartnerTemplate.class);
        System.out.println("Partner template form API response: " + partnerTemplate);
        return partnerTemplate;

    }
    public PartnerTemplate getPartnerFromDB(String id) {
        MongoDb.init();
        MongoCollection<Document> partnerCollection = MongoDb.getPartnerCollection();

        ObjectId objectId = new ObjectId(id);

        Document partner = partnerCollection.find(Filters.eq("_id", objectId)).first();


        partner.put("_id", partner.getObjectId("_id").toHexString());
        String postJson = partner.toJson();

        PartnerTemplate partnerTemplate = null;
        try {
            partnerTemplate = objectMapper.readValue(postJson, PartnerTemplate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            MongoDb.closeConnection();
        }
        System.out.println("Post template from database: " + partnerTemplate);
        return partnerTemplate;
    }

    public boolean isPartnerDeleted(String postId) {
        try {
            MongoDb.init();
            MongoCollection<Document> partnerCollection = MongoDb.getPartnerCollection();

            ObjectId objectId = new ObjectId(postId);

            Document post = partnerCollection.find(Filters.eq("_id", objectId)).first();

            return post == null;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ObjectId format for postId: " + postId);
            return true;
        } finally {
            MongoDb.closeConnection();
        }
    }
}
