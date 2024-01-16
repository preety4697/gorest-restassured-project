package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2" ;
    }

    @Test
    public static void usersExtract()
    {
        response =
                given()
                        .queryParam("page" ,1)
                        .queryParam("per_page" ,20)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200);

//        1. Extract  All Ids
        List<Integer> allIds = response.extract().path("id");
        System.out.println("All Ids : " +allIds);


//        2. Extract the all Names
        List<String> names = response.extract().path("name");
        System.out.println("All names : " +names);

//        3. Extract the name of 5 th object
        String name = response.extract().path("[4].name");
        System.out.println("Name is : " +name );

//        4. Extract the names of all object whose status = inactive
        List<String> namesOfAllObject =  response.extract().path("findAll{it.status == 'inactive'}.name");   //No data so no data.findAll
        System.out.println("Names of all object whose status = inactive : " +namesOfAllObject);

//        5. Extract the gender of all the object whose status = active
        List<String> genderOfAllObject = response.extract().path("findAll{it.status == 'active'}.gender");  //Only findAll
        System.out.println("Gender of all the object :" +genderOfAllObject);

//        6. Print the names of the object whose gender = female
        List<String> namesOfTheObject = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("Names of the object :" +namesOfTheObject);

//        7. Get all the emails of the object where status = inactive
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("All emails :" +allEmails );

//        8. Get the ids of the object where gender = male
        List<Integer> idsOfMale = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("Ids of Male :" +idsOfMale);

//        9. Get all the status
        List<String> allStatuses = response.extract().path("status");
        System.out.println("All statuses: " + allStatuses);

//        10. Get email of the object where name = Vidya Nayar
        String email =  response.extract().path("[15].email");
        System.out.println("Email of Vidya Nayar: " +email);

//        11. Get gender of id = 5914124
        String gender = response.extract().path("[11].gender");
        System.out.println("Gender of given id is : " +gender);
    }
}
