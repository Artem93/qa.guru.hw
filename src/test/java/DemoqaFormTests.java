import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoqaFormTests {
    @BeforeAll
    static void Precondition() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1280";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkFormSubmit() {
        open("/automation-practice-form");
        final var firstName = "Artem";
        final var lastName = "L";
        final var email = "a@mail.com";
        final var gender = "Male";
        final var hobbies = "Music";
        final var number = "8005553535";
        final var dateOfBirth = new String[]{"January", "1900", "1"};
        final var subjects = "Computer Science";
        final var fileName = "test.jpg";
        final var fileNamePath = "src/test/resources/" + fileName;
        final var address = "Russia";
        final var state = "Rajasthan";
        final var city = "Jaipur";

        SelenideElement form = $("#userForm");

        SelenideElement userNameWrapper = form.$("#userName-wrapper");
        userNameWrapper.$("#firstName").setValue(firstName);
        userNameWrapper.$("#lastName").setValue(lastName);

        form.$("#userEmail-wrapper").$("#userEmail").setValue(email);
        form.$("#genterWrapper").$(byText(gender)).click();
        form.$("#userNumber-wrapper").$("#userNumber").setValue(number);

        SelenideElement dateWrapper = form.$("#dateOfBirth-wrapper");
        dateWrapper.$("#dateOfBirthInput").click();
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__month-select").selectOption(dateOfBirth[0]);
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__year-select").selectOption(dateOfBirth[1]);
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__month").find(byText(dateOfBirth[2])).click();

        form.$("#subjectsWrapper").$("#subjectsInput")
                .setValue(subjects)
                .pressEnter();

        form.$("#hobbiesWrapper").find(byText(hobbies)).click();
        form.$("#uploadPicture").uploadFile(new File(fileNamePath));
        form.$("#currentAddress-wrapper").$("#currentAddress").setValue(address);

        SelenideElement stateCityWrapper = form.$("#stateCity-wrapper");
        stateCityWrapper.$("#state").click();
        stateCityWrapper.find(byText(state)).click();
        stateCityWrapper.$("#city").click();
        stateCityWrapper.find(byText(city)).click();

        form.$("#submit").click();


        SelenideElement modalWindow = $(".modal-dialog");
        modalWindow.$("#example-modal-sizes-title-lg")
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        modalWindow.$(".table-responsive")
                .shouldHave(text(String.format("Student Name %s %s", firstName, lastName)))
                .shouldHave(text(String.format("Student Email %s", email)))
                .shouldHave(text(String.format("Gender %s", gender)))
                .shouldHave(text(String.format("Mobile %s", number)))
                .shouldHave(text(String.format("Date of Birth %s %s,%s",
                        Integer.parseInt(dateOfBirth[2]) > 9 ? dateOfBirth[2] : "0" + dateOfBirth[2],
                        dateOfBirth[0], dateOfBirth[1])))
                .shouldHave(text(String.format("Subjects %s", subjects)))
                .shouldHave(text(String.format("Hobbies %s", hobbies)))
                .shouldHave(text(String.format("Picture %s", fileName)))
                .shouldHave(text(String.format("Address %s", address)))
                .shouldHave(text(String.format("State and City %s %s", state, city)));
    }

    @Test
    void checkFormElements() {
        open("/automation-practice-form");

        SelenideElement form = $("#userForm");
        form.shouldBe(visible);

        SelenideElement userNameWrapper = form.$("#userName-wrapper");
        userNameWrapper.$("#userName-label")
                .shouldBe(visible)
                .shouldHave(text("Name"));
        userNameWrapper.$("#firstName")
                .shouldBe(visible)
                .shouldBe(editable);
        userNameWrapper.$("#lastName")
                .shouldBe(visible)
                .shouldBe(editable);

        SelenideElement emailNameWrapper = form.$("#userEmail-wrapper");
        emailNameWrapper.$("#userEmail-label")
                .shouldBe(visible)
                .shouldHave(text("Email"));
        emailNameWrapper.$("#userEmail")
                .shouldBe(visible)
                .shouldBe(editable);

        SelenideElement genderWrapper = form.$("#genterWrapper");
        genderWrapper.shouldHave(text("Gender"));
        genderWrapper.$(byText("Male"))
                .shouldBe(interactable)
                .click();
        genderWrapper.$(byText("Female"))
                .shouldBe(interactable);
        genderWrapper.$(byText("Other"))
                .shouldBe(interactable);

        SelenideElement numberWrapper = form.$("#userNumber-wrapper");
        numberWrapper.$("#userNumber-label")
                .shouldBe(visible)
                .shouldHave(text("Mobile(10 Digits)"));
        numberWrapper.$("#userNumber")
                .shouldBe(visible)
                .shouldBe(interactable);

        SelenideElement dateWrapper = form.$("#dateOfBirth-wrapper");
        dateWrapper.$("#dateOfBirth-label")
                .shouldBe(visible)
                .shouldHave(text("Date of Birth"));
        dateWrapper.$("#dateOfBirthInput")
                .shouldBe(visible)
                .shouldBe(interactable)
                .click();
        dateWrapper.$("#dateOfBirth").shouldBe(visible);
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__month").find(byText("1")).click();

        SelenideElement subjectsWrapper = form.$("#subjectsWrapper");
        subjectsWrapper.$("#subjects-label")
                .shouldBe(visible)
                .shouldHave(text("Subjects"));
        subjectsWrapper.$("#subjectsInput")
                .shouldBe(visible)
                .shouldBe(interactable);

        SelenideElement hobbiesWrapper = form.$("#hobbiesWrapper");
        hobbiesWrapper.$("#subjects-label")
                .shouldBe(visible)
                .shouldHave(text("Hobbies"));
        hobbiesWrapper.$("#hobbies-checkbox-1")
                .shouldBe(interactable);
        hobbiesWrapper.find(byText("Sports"))
                .shouldBe(visible);
        hobbiesWrapper.$("#hobbies-checkbox-2")
                .shouldBe(interactable);
        hobbiesWrapper.find(byText("Reading"))
                .shouldBe(visible);
        hobbiesWrapper.$("#hobbies-checkbox-3")
                .shouldBe(interactable);
        hobbiesWrapper.find(byText("Music"))
                .shouldBe(visible);

        form.$("#uploadPicture").shouldBe(visible);
        SelenideElement addressWrapper = form.$("#currentAddress-wrapper");
        addressWrapper.$("#currentAddress-label")
                .shouldBe(visible)
                .shouldHave(text("Current Address"));
        addressWrapper.$("#currentAddress")
                .shouldBe(visible)
                .shouldBe(interactable);

        SelenideElement stateCityWrapper = form.$("#stateCity-wrapper");
        stateCityWrapper.$("#stateCity-label")
                .shouldBe(visible)
                .shouldHave(text("State and City"));
        stateCityWrapper.$("#state")
                .shouldBe(visible)
                .shouldBe(interactable);
        stateCityWrapper.$("#city")
                .shouldBe(visible);
        
        form.$("#submit")
                .shouldBe(visible)
                .shouldBe(interactable);
    }
}
