package guru.qa.hw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement profileWrapper = $(".profile-wrapper");
    private final SelenideElement modalWindow = $(".modal-content");
    private final SelenideElement modalWindowButtonOk = modalWindow.$("#closeSmallModal-ok");
    private final ElementsCollection deleteBookButtons = $$("#delete-record-undefined");

    @Step("Открыть страницу /profile")
    public ProfilePage openPage() {
        open("/profile");
        profileWrapper.shouldBe(visible);
        removeBanner();
        return this;
    }

    @Step("Удалить первую книгу в списке")
    public ProfilePage deleteBookInList() {
        deleteBookButtons.first()
                .shouldBe(visible)
                .click();
        modalWindow
                .shouldBe(visible);
        modalWindowButtonOk
                .shouldBe(visible)
                .click();
        switchTo().alert().accept();
        Assertions.assertEquals(0, deleteBookButtons.size(), "Кнопки не должно быть");
        return this;
    }

    private void removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("document.getElementById('Ad.Plus-970x250-1').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
