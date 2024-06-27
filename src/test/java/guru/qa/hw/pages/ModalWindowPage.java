package guru.qa.hw.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowPage {
    public static final String constName = "Student Name";
    public static final String constEmail = "Student Email";
    public static final String constGender = "Gender";
    public static final String constMobile = "Mobile";
    public static final String constBirthday = "Date of Birth";
    public static final String constSubject = "Subjects";
    public static final String constHobbies = "Hobbies";
    public static final String constPicture = "Picture";
    public static final String constAddress = "Address";
    public static final String constStateCity = "State and City";

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
