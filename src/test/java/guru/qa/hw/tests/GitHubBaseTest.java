package guru.qa.hw.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class GitHubBaseTest {
    @BeforeAll
    static void precondition() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1280";
        Configuration.pageLoadStrategy = "eager";
    }
}
