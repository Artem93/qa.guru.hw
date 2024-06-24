package guru.qa.hw.tests;

import static com.github.javafaker.Faker.instance;
import static guru.qa.hw.utils.GenerateTestData.*;

public class TestData {
    private final States selectState = generateState();

    String firstName = instance().name().firstName();
    String lastName = instance().name().lastName();
    String email = instance().internet().emailAddress();
    String invalidEmail = email + "ololo";
    String gender = generateGender();
    String number = instance().phoneNumber().subscriberNumber(10);
    String invalidNumber = instance().phoneNumber().subscriberNumber(4);
    Birthday birthday = generateBirthday();
    String subject = generateSubject();
    String hobbies = generateHobbies();
    String fileName = "test.jpg";
    String address = instance().address().fullAddress();
    String state = selectState.state;
    String city = generateCity(selectState);
}
