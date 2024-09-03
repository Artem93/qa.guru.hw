package guru.qa.hw.api;

import guru.qa.hw.models.BookModel;

import static guru.qa.hw.specs.Specs.requestSpec;
import static guru.qa.hw.specs.Specs.response200Spec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class BooksApi {
    public static BookModel getBookInfo(String isbn) {
        return step(String.format("Получение инфо о книге %s по API", isbn), () ->
                given(requestSpec)
                        .get("/BookStore/v1/Book?ISBN=" + isbn)
                        .then()
                        .spec(response200Spec)
                        .extract().as(BookModel.class)
        );
    }
}
