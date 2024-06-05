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
        SelenideElement form = $("#userForm");

        SelenideElement userNameWrapper = form.$("#userName-wrapper");
        userNameWrapper.$("#firstName").setValue("Artem");
        userNameWrapper.$("#lastName").setValue("L");

        form.$("#userEmail-wrapper").$("#userEmail").setValue("a@mail.com");
        form.$("#genterWrapper").$(byText("Male")).click();
        form.$("#userNumber-wrapper").$("#userNumber").setValue("8005553535");

        SelenideElement dateWrapper = form.$("#dateOfBirth-wrapper");
        dateWrapper.$("#dateOfBirthInput").click();
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__month-select").selectOption("January");
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__year-select").selectOption("1900");
        dateWrapper.$("#dateOfBirth")
                .$(".react-datepicker__month").find(byText("1")).click();

        form.$("#subjectsWrapper").$("#subjectsInput")
                .setValue("Computer Science")
                .pressEnter();

        form.$("#hobbiesWrapper").find(byText("Music")).click();
        form.$("#uploadPicture").uploadFile(new File("src/test/resources/test.jpg"));
        form.$("#currentAddress-wrapper").$("#currentAddress").setValue("Russia");

        SelenideElement stateCityWrapper = form.$("#stateCity-wrapper");
        stateCityWrapper.$("#state").click();
        stateCityWrapper.find(byText("Rajasthan")).click();
        stateCityWrapper.$("#city").click();
        stateCityWrapper.find(byText("Jaipur")).click();

        form.$("#submit").click();

        SelenideElement modalWindow = $(".modal-dialog");
        modalWindow.$("#example-modal-sizes-title-lg")
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        modalWindow.$(".table-responsive")
                .shouldHave(text("Student Name Artem L"))
                .shouldHave(text("Student Email a@mail.com"))
                .shouldHave(text("Gender Male"))
                .shouldHave(text("Mobile 8005553535"))
                .shouldHave(text("Date of Birth 01 January,1900"))
                .shouldHave(text("Subjects Computer Science"))
                .shouldHave(text("Hobbies Music"))
                .shouldHave(text("Picture test.jpg"))
                .shouldHave(text("Address Russia"))
                .shouldHave(text("State and City Rajasthan Jaipur"));
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
