package guru.qa.hw.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.hw.helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DemoQaTestBase {
    @BeforeAll
    static void setUp() {
        var browser = ":".split(System.getProperty("browser", "chrome:100.0"))[0];
        var version = ":".split(System.getProperty("browser", "chrome:100.0"))[1];
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.remote = System.getProperty(
                "remoteUrl",
                "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        Configuration.browserSize = System.getProperty(
                "resolution",
                "1920x1280");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void setTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void post() {
        Attachments.attachPage();
        Attachments.attachScreenshot();
        Attachments.addVideo();
        closeWebDriver();
    }
}
