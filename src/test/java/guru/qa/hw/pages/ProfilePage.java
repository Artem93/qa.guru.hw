package guru.qa.hw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.hw.api.BooksApi;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement profileWrapper = $(".profile-wrapper");
    private final SelenideElement modalWindow = $(".modal-content");
    private final SelenideElement modalWindowButtonOk = modalWindow.$("#closeSmallModal-ok");
    private final ElementsCollection deleteBookButtons = $$("#delete-record-undefined");
    private final SelenideElement booksTable = $(".rt-tbody");

    @Step("Открыть страницу /profile")
    public ProfilePage openPage() {
        open("/profile");
        profileWrapper.shouldBe(visible);
        removeBanner();
        return this;
    }

    @Step("Клик на кнопку удаления книги")
    public ProfilePage clickOnDeleteFirstIcoInList() {
        deleteBookButtons.first()
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Нажатие на 'Ok' в окне подтверждения удаления")
    public ProfilePage pressOkButtonInModal() {
        modalWindow
                .shouldBe(visible);
        modalWindowButtonOk
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Нажатие на Enter при появлении системного окна браузера")
    public ProfilePage acceptSystemWindow() {
        switchTo().alert().accept();
        return this;
    }

    @Step("Проверка удаления книги {isbn}")
    public ProfilePage checkBooksRemoved(String isbn) {
        var book = BooksApi.getBookInfo(isbn);

        booksTable
                .shouldNotHave(text(book.getTitle()))
                .shouldNotHave(text(book.getAuthor()))
                .shouldNotHave(text(book.getPublisher()));
        return this;
    }

    private void removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("document.getElementById('Ad.Plus-970x250-1').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
