package com.christian;


import com.jayway.restassured.RestAssured;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;

@RunWith(JUnitParamsRunner.class)
public class DataDrivenTest {

    String accessToken = "Anc-qtV43H-kDzKQn2K33kkWw_0yFUcd24aNqWZFIP8LSeAsDAdpADAAAYRRRSEBbg2AND8AAAAA";

    @Before
    public void startUp() {
        RestAssured.baseURI = "https://api.pinterest.com/v1";
        RestAssured.port = 8080;
    }

    @Test
    @FileParameters("src/test/resources/dataSource")
    public void dataTest(String pin, String expectedUrl) {

        String response = given().auth().oauth2(accessToken)
                .when().get("/pins/"+pin).asString();

        String actualPinUrl = from(response).get("data.url");
        Assert.assertEquals(expectedUrl,actualPinUrl);

    }

}
