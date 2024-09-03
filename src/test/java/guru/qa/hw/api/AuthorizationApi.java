package guru.qa.hw.api;

import guru.qa.hw.models.AuthRequestModel;
import guru.qa.hw.models.AuthResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.hw.helpers.Constants.*;
import static guru.qa.hw.specs.Specs.requestSpec;
import static guru.qa.hw.specs.Specs.response200Spec;
import static guru.qa.hw.tests.demoqa.TestData.userName;
import static guru.qa.hw.tests.demoqa.TestData.userPassword;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static void setCookies() {
        step("Авторизация путем подмены cookie", () -> {
            AuthResponseModel authResponseModel = AuthorizationApi.getAuthorizationResponse();
            open("/images/Toolsqa.jpg");
            getWebDriver().manage().addCookie(new Cookie(userIdConst, authResponseModel.getUserId()));
            getWebDriver().manage().addCookie(new Cookie(expiresConst, authResponseModel.getExpires()));
            getWebDriver().manage().addCookie(new Cookie(userTokenConst, authResponseModel.getToken()));
        });
    }

    private static AuthResponseModel getAuthorizationResponse() {
        AuthRequestModel authModel = new AuthRequestModel();
        authModel.setUserName(userName);
        authModel.setPassword(userPassword);

        return step(String.format("Добавление юзера %s", userName), () ->
                given(requestSpec)
                        .body(authModel)
                        .when()
                        .post("Account/v1/Login")
                        .then()
                        .spec(response200Spec)
                        .extract().as(AuthResponseModel.class)
        );
    }
}
