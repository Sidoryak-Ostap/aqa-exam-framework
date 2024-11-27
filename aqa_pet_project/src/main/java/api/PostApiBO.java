package api;

import api.templates.PostTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import mongo.MongoDb;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PostApiBO {

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

    public PostTemplate createPost(String token)  {

        String createPostUrl = "http://localhost:5000/api/admin/createPost";
        File photo = new File(getClass().getClassLoader().getResource("images/Landscape-Color.jpg").getFile());

        Response createPostResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .multiPart("ukrTitle", "Some title")
                .multiPart("ukrDescription", "Some title")
                .multiPart("ukrShortDescription", "Some title")
                .multiPart("engTitle", "Some title")
                .multiPart("engDescription", "Some title")
                .multiPart("engShortDescription", "Some title")
                .multiPart("photos", photo, "image/jpeg")
                .when()
                .post(createPostUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();


        PostTemplate postTemplate = createPostResponse.jsonPath().getObject("post", PostTemplate.class);
        System.out.println("Post template form API response: " + postTemplate);

        return postTemplate;

    }

    public PostTemplate updatePost(String token, String postId)  {

        String updatePostUrl = "http://localhost:5000/api/admin/editPost";
        File photo = new File(getClass().getClassLoader().getResource("images/Landscape-Color.jpg").getFile());

        Response updatePostResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .multiPart("ukrTitle", "Updated some title")
                .multiPart("ukrDescription", "Some title")
                .multiPart("ukrShortDescription", "Some title")
                .multiPart("engTitle", "Some title")
                .multiPart("engDescription", "Some title")
                .multiPart("engShortDescription", "Some title")
                .multiPart("postId", postId)
                .multiPart("photos", photo, "image/jpeg")
                .when()
                .patch(updatePostUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        PostTemplate postTemplate = updatePostResponse.jsonPath().getObject("post", PostTemplate.class);
        System.out.println("Post template form API response: " + postTemplate);
        return postTemplate;

    }

    public PostTemplate deletePost(String token, String postId){
        String deletePostUrl = "http://localhost:5000/api/admin/deletePost";
        String body = "{\"postId\":\""+postId+"\"}";

        Response deletePostResponse = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .delete(deletePostUrl)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();

        PostTemplate postTemplate = deletePostResponse.jsonPath().getObject("post", PostTemplate.class);
        System.out.println("Post template form API response: " + postTemplate);
        return postTemplate;

    }

    public PostTemplate getPostFromDB(String id) {
        MongoDb.init();
        MongoCollection<Document> postsCollection = MongoDb.getPostsCollection();

        ObjectId objectId = new ObjectId(id);

        Document post = postsCollection.find(Filters.eq("_id", objectId)).first();

        post.put("_id", post.getObjectId("_id").toHexString());
        String postJson = post.toJson();

        PostTemplate postTemplate = null;
        try {
            postTemplate = objectMapper.readValue(postJson, PostTemplate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            MongoDb.closeConnection();
        }
        System.out.println("Post template from database: " + postTemplate);
        return postTemplate;
    }

    public boolean isPostDeleted(String postId) {
        try {
            MongoDb.init();
            MongoCollection<Document> postsCollection = MongoDb.getPostsCollection();

            ObjectId objectId = new ObjectId(postId);

            Document post = postsCollection.find(Filters.eq("_id", objectId)).first();

            return post == null;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ObjectId format for postId: " + postId);
            return true;
        } finally {
            MongoDb.closeConnection();
        }
    }
}
