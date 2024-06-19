package guru.qa.hw.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class DemoQaTestBase {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1280";
        Configuration.pageLoadStrategy = "eager";
    }
}
