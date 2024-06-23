package guru.qa.hw.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowPage {

    private final SelenideElement modalWindow = $(".modal-dialog");
    private final SelenideElement modalWindowHeader = modalWindow.$("#example-modal-sizes-title-lg");
    private final SelenideElement modalWindowTable = modalWindow.$(".table-responsive");

    public ModalWindowPage checkHeader() {
        modalWindowHeader
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public ModalWindowPage checkTableValue(String key, String value) {
        modalWindowTable
                .$(byText(key))
                .sibling(0)
                .shouldHave(text(value));
        return this;
    }

    public void notExist() {
        modalWindow.shouldNotBe(exist);
    }
}
