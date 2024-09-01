package guru.qa.hw.tests.demoqa;

import guru.qa.hw.helpers.WithLogin;
import guru.qa.hw.models.BookAddRequestModel;
import guru.qa.hw.models.IsbmModel;
import guru.qa.hw.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

import static guru.qa.hw.helpers.Constants.userIdConst;
import static guru.qa.hw.helpers.Constants.userTokenConst;
import static guru.qa.hw.helpers.CookieManager.getCookieValue;
import static guru.qa.hw.specs.Specs.*;
import static guru.qa.hw.tests.demoqa.TestData.testIsbn;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoQaBookTests extends DemoQaTestBase {
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tags({
            @Tag("regression"),
            @Tag("smoke")
    })
    @DisplayName("")
    @WithLogin
    void removeBookByUITest() {
        BookAddRequestModel bookAddRequestModel = new BookAddRequestModel();
        IsbmModel book = new IsbmModel();
        book.setIsbn(testIsbn);
        bookAddRequestModel.setUserId(getCookieValue(userIdConst));
        bookAddRequestModel.setCollectionOfIsbns(List.of(book));
        var userId = getCookieValue(userIdConst);

        step(String.format("Удаление всех книг из списка для юзера %s", userId), () -> given(requestSpec)
                .header("authorization", "Bearer " + getCookieValue(userTokenConst))
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(response204Spec)
        );
        step(String.format("Добавление книги с isbn: %s", testIsbn), () -> given(requestSpec)
                .header("authorization", "Bearer " + getCookieValue(userTokenConst))
                .body(bookAddRequestModel)
                .when()
                .post("BookStore/v1/Books")
                .then()
                .spec(response201Spec)
        );
        profilePage
                .openPage()
                .deleteBookInList();
    }
}
