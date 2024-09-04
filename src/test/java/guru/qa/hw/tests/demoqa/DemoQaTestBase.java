package guru.qa.hw.tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.hw.config.ConfigDriver;
import guru.qa.hw.helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DemoQaTestBase {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";

        var config = new ConfigDriver().getTestConfig();

        if (config.getIsRemote()) {
            Configuration.browser = config.getBrowser().split(":")[0];
            Configuration.browserVersion = config.getBrowser().split(":")[1];
        } else {
            Configuration.browser = config.getBrowserName();
            Configuration.browserVersion = config.getBrowserVersion();
        }
        Configuration.remote = config.getRemoteUrl();
        Configuration.browserSize = config.getBrowserSize();

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
