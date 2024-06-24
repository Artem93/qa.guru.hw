package guru.qa.hw.tests;

import guru.qa.hw.pages.ModalWindowPage;
import guru.qa.hw.pages.PracticeFormPage;
import org.junit.jupiter.api.Test;

import static guru.qa.hw.pages.ModalWindowPage.*;

public class DemoQaFormTests extends DemoQaTestBase {

    @Test
    void checkFillAllFieldsFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();
        practiceFormPage.openPage();

        practiceFormPage.setFirstName(td.firstName).setLastName(td.lastName).setEmail(td.email).setGender(td.gender).setNumber(td.number).setBirthday(td.birthday.year, td.birthday.month, td.birthday.day).setSubject(td.subject).setHobbies(td.hobbies).uploadFile(td.filePath).setAddress(td.address).selectState(td.state).selectCity(td.city)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader().checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName)).checkTableValue(constEmail, td.email).checkTableValue(constGender, td.gender).checkTableValue(constMobile, td.number).checkTableValue(constBirthday, String.format("%s %s,%s", td.birthday.dayWithZero, td.birthday.month, td.birthday.year)).checkTableValue(constSubject, td.subject).checkTableValue(constHobbies, td.hobbies).checkTableValue(constPicture, td.fileName).checkTableValue(constAddress, td.address).checkTableValue(constStateCity, String.format("%s %s", td.state, td.city));
    }

    @Test
    void checkFillRequirementFieldsFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage.setFirstName(td.firstName).setLastName(td.lastName).setGender(td.gender).setNumber(td.number)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader().checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName)).checkTableValue(constGender, td.gender).checkTableValue(constMobile, td.number);
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
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage.setFirstName(td.firstName).setLastName(td.lastName).setGender(td.gender).setNumber(td.invalidNumber)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @Test
    void checkInvalidEmailFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage.setFirstName(td.firstName).setLastName(td.lastName).setEmail(td.invalidEmail).setGender(td.gender).setNumber(td.number)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }
}
