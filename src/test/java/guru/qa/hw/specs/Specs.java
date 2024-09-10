package guru.qa.hw.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.hw.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class Specs {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().uri()
            .log().body()
            .log().headers();

    public static ResponseSpecification response200Spec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(JSON)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .log(LogDetail.HEADERS)
            .build();

    public static ResponseSpecification response201Spec = new ResponseSpecBuilder()
            .log(BODY)
            .log(HEADERS)
            .log(STATUS)
            .expectContentType(JSON)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification response204Spec = new ResponseSpecBuilder()
            .log(BODY)
            .log(HEADERS)
            .log(STATUS)
            .expectStatusCode(204)
            .build();
}
