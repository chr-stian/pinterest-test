package com.christian;

import com.jayway.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;

public class DemoTest {
    String accessToken = "Anc-qtV43H-kDzKQn2K33kkWw_0yFUcd24aNqWZFIP8LSeAsDAdpADAAAYRRRSEBbg2AND8AAAAA";

    @Before
    public void startUp() {
        RestAssured.baseURI = "https://api.pinterest.com/v1";
        RestAssured.port = 8080;
    }

    @Test
    public void getBoardURLTest(){
        String response = given().auth().oauth2(accessToken)
                .when().get("/boards/ohpen/we-are-ohpen/").asString();

        String actualBoardURL = from(response).get("data.url");
        String expectedBoardURL  = "https://www.pinterest.com/ohpen/we-are-ohpen/";
        Assert.assertEquals(expectedBoardURL,actualBoardURL);
    }

    @Test
    public void getBoardIDTest(){

        String response = given().auth().oauth2(accessToken)
                .when().get("/boards/ohpen/we-are-ohpen/").asString();

        String actualBoardID = from(response).get("data.id");
        String expectedBoardID  = "402579722879371092";
        Assert.assertEquals(expectedBoardID,actualBoardID);
    }

    @Test
    public void createBoardTest(){
        given().auth().oauth2(accessToken).param("name", "chris_test_1")
                .when().post("/boards/").then().statusCode(201);
    }

    @Test
    public void deleteBoardTest(){

        String boardToDelete = "to_delete_board_A";
        given().auth().oauth2(accessToken).param("name", boardToDelete)
                .when().post("/boards/").then().statusCode(201);

        given().auth().oauth2(accessToken)
                .when().delete("/boards/christiant0312/"+boardToDelete).then().statusCode(201);
    }



}
