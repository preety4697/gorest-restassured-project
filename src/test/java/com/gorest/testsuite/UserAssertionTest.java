package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest  {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";

    }
    @Test
    public void userAssert(){
        response =
                given()
                        .queryParam("page",1)
                        .queryParam("per_page",20)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200);

//        1. Verify the if the total record is 20

            response.body("size()", equalTo(20));

//        2. Verify the if the name of id = 5914197 is equal to ”Chidambaram Talwar”
            response.body("[9].name", equalTo("Chidambaram Talwar"));

//        3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
        response.body("[14].name", equalTo("Dr. Datta Embranthiri"));

//        4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
        response.body("name", hasItems("Ekalavya Embranthiri","Kailash Pillai","Arnesh Singh"));

//        5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
        response.body("[14].email" ,equalTo("dr_embranthiri_datta@casper-gerlach.test"));

//        6. Verify the status is “Active” of user name is “Amaresh Rana”
        response.body("[2].status" ,equalTo("active"));
//        7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”




    }
}
