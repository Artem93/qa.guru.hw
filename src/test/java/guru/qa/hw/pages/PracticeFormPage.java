package guru.qa.hw.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement form = $("#userForm");
    private final SelenideElement header = $(".text-center");
    private final SelenideElement userNameWrapper = form.$("#userName-wrapper");
    private final SelenideElement formFirstName = userNameWrapper.$("#firstName");
    private final SelenideElement formLastName = userNameWrapper.$("#lastName");
    private final SelenideElement formEmail = form.$("#userEmail-wrapper").$("#userEmail");
    private final SelenideElement formGender = form.$("#genterWrapper");
    private final SelenideElement formNumber = form.$("#userNumber-wrapper").$("#userNumber");
    private final SelenideElement datePicker = form.$(".react-datepicker");
    private final SelenideElement dateOfBirthInput = form.$("#dateOfBirthInput");
    private final SelenideElement formSubject = form.$("#subjectsWrapper").$("#subjectsInput");
    private final SelenideElement formHobbies = form.$("#hobbiesWrapper");
    private final SelenideElement formUploadFile = form.$("#uploadPicture");
    private final SelenideElement formAddress = form.$("#currentAddress-wrapper").$("#currentAddress");
    private final SelenideElement stateCityWrapper = form.$("#stateCity-wrapper");
    private final SelenideElement formState = stateCityWrapper.$("#state");
    private final SelenideElement formCity = stateCityWrapper.$("#city");
    private final SelenideElement submitButton = form.$("#submit");

    @Step("Открыть страницу /automation-practice-form")
    public void openPage() {
        open("/automation-practice-form");
        header.shouldHave(text("Practice Form"));
        removeBanner();
    }

    private void removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#Ad.Plus-970x250-1').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Step("Задать имя: {0}")
    public PracticeFormPage setFirstName(String name) {
        formFirstName.setValue(name);
        return this;
    }

    @Step("Задать фамилию: {0}")
    public PracticeFormPage setLastName(String name) {
        formLastName.setValue(name);
        return this;
    }

    @Step("Задать email: {0}")
    public PracticeFormPage setEmail(String email) {
        formEmail.setValue(email);
        return this;
    }

    @Step("Задать пол: {0}")
    public PracticeFormPage setGender(String gender) {
        formGender.$(byText(gender)).click();
        return this;
    }

    @Step("Задать номер: {0}")
    public PracticeFormPage setNumber(String number) {
        formNumber.setValue(number);
        return this;
    }

    @Step("Задать др: {0} {1} {2}")
    public PracticeFormPage setBirthday(String year, String month, String day) {
        dateOfBirthInput.click();
        datePicker.shouldBe(visible);
        new CalendarWidgetPage().setBirthday(year, month, day);
        return this;
    }

    @Step("Задать предмет: {0}")
    public PracticeFormPage setSubject(String subject) {
        formSubject.setValue(subject).pressEnter();
        return this;
    }

    @Step("Задать хобби: {0}")
    public PracticeFormPage setHobbies(String hobby) {
        formHobbies.find(byText(hobby)).click();
        return this;
    }

    @Step("Загрузить файл")
    public PracticeFormPage uploadFile(String path) {
        formUploadFile.uploadFromClasspath(path);
        return this;
    }

    @Step("Задать адрес: {0}")
    public PracticeFormPage setAddress(String address) {
        formAddress.setValue(address);
        return this;
    }

    @Step("Задать штат: {0}")
    public PracticeFormPage selectState(String state) {
        formState.click();
        stateCityWrapper.find(byText(state)).click();
        return this;
    }

    @Step("Задать город: {0}")
    public PracticeFormPage selectCity(String city) {
        formCity.click();
        stateCityWrapper.find(byText(city)).click();
        return this;
    }

    @Step("Тапнуть на кнопку")
    public void submitForm() {
        submitButton.click();
    }
}
