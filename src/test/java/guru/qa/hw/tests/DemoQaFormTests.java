package guru.qa.hw.tests;

import guru.qa.hw.pages.ModalWindowPage;
import guru.qa.hw.pages.PracticeFormPage;
import org.junit.jupiter.api.Test;

public class DemoQaFormTests extends DemoQaTestBase {

    @Test
    void checkFillAllFieldsFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();

        practiceFormPage
                .setFirstName("Artem")
                .setLastName("L")
                .setEmail("a@mail.com")
                .setGender(PracticeFormPage.Gender.Male)
                .setNumber("8005553535")
                .setBirthday("1900", "January", "1")
                .setSubject("Computer Science")
                .setHobbies("Music")
                .uploadFile("src/test/resources/test.jpg")
                .setAddress("Russia")
                .selectState("Rajasthan")
                .selectCity("Jaipur")
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue("Student Name", "Artem L")
                .checkTableValue("Student Email", "a@mail.com")
                .checkTableValue("Gender", "Male")
                .checkTableValue("Mobile", "8005553535")
                .checkTableValue("Date of Birth", "01 January,1900")
                .checkTableValue("Subjects", "Computer Science")
                .checkTableValue("Hobbies", "Music")
                .checkTableValue("Picture", "test.jpg")
                .checkTableValue("Address", "Russia")
                .checkTableValue("State and City", "Rajasthan Jaipur");
    }

    @Test
    void checkFillRequirementFieldsFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName("Artem")
                .setLastName("L")
                .setGender(PracticeFormPage.Gender.Male)
                .setNumber("8005553535")
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue("Student Name", "Artem L")
                .checkTableValue("Gender", "Male")
                .checkTableValue("Mobile", "8005553535");
    }

    @Test
    void checkEmptyFieldFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage.submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @Test
    void checkInvalidPhoneFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName("Artem")
                .setLastName("L")
                .setGender(PracticeFormPage.Gender.Male)
                .setNumber("800555353d")
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @Test
    void checkInvalidEmailFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName("Artem")
                .setLastName("L")
                .setEmail("aaa")
                .setGender(PracticeFormPage.Gender.Male)
                .setNumber("800555353")
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }
}
