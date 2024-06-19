package guru.qa.hw.pages;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {
    private final SelenideElement form = $("#userForm");
    private final SelenideElement header = $(".text-center");

    private final SelenideElement userNameWrapper = form.$("#userName-wrapper");
    private final SelenideElement formFirstName = userNameWrapper.$("#firstName");
    private final SelenideElement formLastName = userNameWrapper.$("#lastName");

    private final SelenideElement formEmail = form.$("#userEmail-wrapper").$("#userEmail");
    private final SelenideElement formGender = form.$("#genterWrapper");
    private final SelenideElement formNumber = form.$("#userNumber-wrapper").$("#userNumber");

    private final SelenideElement dateWrapper = form.$("#dateOfBirth-wrapper");

    private final SelenideElement formSubject = form.$("#subjectsWrapper").$("#subjectsInput");
    private final SelenideElement formHobbies = form.$("#hobbiesWrapper");
    private final SelenideElement formUploadFile = form.$("#uploadPicture");
    private final SelenideElement formAddress = form.$("#currentAddress-wrapper").$("#currentAddress");

    private final SelenideElement stateCityWrapper = form.$("#stateCity-wrapper");
    private final SelenideElement formState = stateCityWrapper.$("#state");
    private final SelenideElement formCity = stateCityWrapper.$("#city");

    private final SelenideElement submitButton = form.$("#submit");

    public enum Gender {
        Male,
        Female,
        Other
    }

    public void openPage() {
        open("/automation-practice-form");
        header.shouldHave(text("Practice Form"));
    }

    public PracticeFormPage setFirstName(String name) {
        formFirstName.setValue(name);
        return this;
    }

    public PracticeFormPage setLastName(String name) {
        formLastName.setValue(name);
        return this;
    }

    public PracticeFormPage setEmail(String email) {
        formEmail.setValue(email);
        return this;
    }

    public PracticeFormPage setGender(Gender gender) {
        formGender.$(byText(gender.toString())).click();
        return this;
    }

    public PracticeFormPage setNumber(String number) {
        formNumber.setValue(number);
        return this;
    }


    public PracticeFormPage setBirthday(String year, String month, String day) {
        dateWrapper.click();
        new CalendarWidgetPage().setBirthday(year, month, day);
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        formSubject.setValue(subject).pressEnter();
        return this;
    }


    public PracticeFormPage setHobbies(String hobby) {
        formHobbies.find(byText(hobby)).click();
        return this;
    }

    public PracticeFormPage uploadFile(String path) {
        formUploadFile.uploadFile(new File(path));
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        formAddress.setValue(address);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        formState.click();
        stateCityWrapper.find(byText(state)).click();
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        formCity.click();
        stateCityWrapper.find(byText(city)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }
}
