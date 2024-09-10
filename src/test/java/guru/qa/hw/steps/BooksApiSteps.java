package guru.qa.hw.steps;

import guru.qa.hw.models.BookAddRequestModel;
import guru.qa.hw.models.IsbnModel;

import java.util.List;

import static guru.qa.hw.helpers.Constants.userIdConst;
import static guru.qa.hw.helpers.Constants.userTokenConst;
import static guru.qa.hw.helpers.CookieManager.getCookieValue;
import static guru.qa.hw.specs.Specs.*;
import static guru.qa.hw.tests.demoqa.TestData.testIsbn;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class BooksApiSteps {

    public BooksApiSteps removeAllBooks() {
        var userId = getCookieValue(userIdConst);
        step(String.format("Удаление всех книг из списка для юзера %s", userId), () -> given(requestSpec)
                .header("authorization", "Bearer " + getCookieValue(userTokenConst))
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(response204Spec)
        );
        return this;
    }

    public BooksApiSteps addBooks(String isbn) {
        BookAddRequestModel bookAddRequestModel = new BookAddRequestModel();
        IsbnModel book = new IsbnModel();
        book.setIsbn(isbn);
        bookAddRequestModel.setUserId(getCookieValue(userIdConst));
        bookAddRequestModel.setCollectionOfIsbns(List.of(book));

        step(String.format("Добавление книги с isbn: %s", testIsbn), () -> given(requestSpec)
                .header("authorization", "Bearer " + getCookieValue(userTokenConst))
                .body(bookAddRequestModel)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(response201Spec)
        );
        return this;
    }
}
