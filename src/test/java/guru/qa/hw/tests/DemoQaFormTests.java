package guru.qa.hw.tests;

import guru.qa.hw.pages.ModalWindowPage;
import guru.qa.hw.pages.PracticeFormPage;
import guru.qa.hw.utils.GenerateTestData.States;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static guru.qa.hw.pages.ModalWindowPage.*;

public class DemoQaFormTests extends DemoQaTestBase {

    @DisplayName("Проверка заполнения всех полей и отображения этих данных в модальном окне")
    @Test
    void checkFillAllFieldsFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();
        practiceFormPage.openPage();

        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setEmail(td.email)
                .setGender(td.gender)
                .setNumber(td.number)
                .setBirthday(td.birthday.year, td.birthday.month, td.birthday.day)
                .setSubject(td.subject)
                .setHobbies(td.hobbies)
                .uploadFile(td.fileName)
                .setAddress(td.address)
                .selectState(td.state)
                .selectCity(td.city)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName))
                .checkTableValue(constEmail, td.email)
                .checkTableValue(constGender, td.gender)
                .checkTableValue(constMobile, td.number)
                .checkTableValue(constBirthday, String.format("%s %s,%s", td.birthday.dayWithZero, td.birthday.month, td.birthday.year))
                .checkTableValue(constSubject, td.subject)
                .checkTableValue(constHobbies, td.hobbies)
                .checkTableValue(constPicture, td.fileName)
                .checkTableValue(constAddress, td.address)
                .checkTableValue(constStateCity, String.format("%s %s", td.state, td.city));
    }

    @DisplayName("Проверка заполнения только требуемых полей и отображения этих данных в модальном окне")
    @Test
    void checkFillRequirementFieldsFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setGender(td.gender)
                .setNumber(td.number)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName))
                .checkTableValue(constGender, td.gender)
                .checkTableValue(constMobile, td.number);
    }

    public static Stream<Arguments> checkFillStatesAndCitiesFieldsFormTest() {
        return Arrays.stream(States.values())
                .flatMap(state -> Arrays.stream(state.cities)
                        .map(city -> Arguments.of(state.state, city)));
    }

    @MethodSource()
    @DisplayName("Проверка заполнения штата: {0} и города {1} и отображения этих данных в модальном окне")
    @ParameterizedTest
    void checkFillStatesAndCitiesFieldsFormTest(String state, String city) {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setGender(td.gender)
                .setNumber(td.number)
                .selectState(state)
                .selectCity(city)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName))
                .checkTableValue(constGender, td.gender)
                .checkTableValue(constStateCity, String.format("%s %s", state, city))
                .checkTableValue(constMobile, td.number);
    }

    @DisplayName("Проверка, что модальное окно не отображается при незаполненных полях")
    @Test
    void checkEmptyFieldFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage.submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @DisplayName("Проверка, что модальное окно не отображается при некорректном номере")
    @Test
    void checkInvalidPhoneFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setGender(td.gender)
                .setNumber(td.invalidNumber)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @DisplayName("Проверка, что модальное окно не отображается при некорректной почте")
    @Test
    void checkInvalidEmailFormTest() {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setEmail(td.invalidEmail)
                .setGender(td.gender)
                .setNumber(td.number)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage.notExist();
    }

    @ValueSource(strings = {"0000000000", "9999999999"})
    @DisplayName("Проверка данных в модальном окне при номера: {0}")
    @ParameterizedTest
    void checkCorrectPhoneFormTest(String phone) {
        TestData td = new TestData();
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.openPage();
        practiceFormPage
                .setFirstName(td.firstName)
                .setLastName(td.lastName)
                .setGender(td.gender)
                .setNumber(phone)
                .submitForm();

        ModalWindowPage modalWindowPage = new ModalWindowPage();
        modalWindowPage
                .checkHeader()
                .checkTableValue(constName, String.format("%s %s", td.firstName, td.lastName))
                .checkTableValue(constGender, td.gender)
                .checkTableValue(constMobile, phone);
    }
}
