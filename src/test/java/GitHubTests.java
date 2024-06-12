import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class GitHubTests {

    @BeforeAll
    static void precondition() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1280";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkSoftAssertionsTest() {
        open("selenide/selenide");

        // Открываем таб
        $("#wiki-tab").click();

        // Ищем страницу через поиск
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").$(byTagAndText("a", "SoftAssertions")).click();

        // Проверяем, что переход произошел
        $(byTagAndText("h1", "SoftAssertions")).shouldBe(visible);

        // Проверяем блок с JUnit5
        $(byText("3. Using JUnit5 extend test class:")).scrollTo().shouldBe(visible);
        $(byText("3. Using JUnit5 extend test class:"))
                .parent().sibling(0).find("pre").shouldHave(
                        text("@ExtendWith({SoftAssertsExtension.class})"),
                        text("class Tests {"),
                        text("@Test"),
                        text("void test() {"),
                        text("Configuration.assertionMode = SOFT;"),
                        text("open(\"page.html\");"),
                        text("$(\"#first\").should(visible).click();"),
                        text("$(\"#second\").should(visible).click();")
                );
    }

    @Test
    void checkPageAiPoweredTest() {
        open("");
        $$(".HeaderMenu-item").find(text(" Solutions ")).hover();
        $(byTagAndText("a", "Enterprise")).click();
        $(byTagAndText("h1", "The AI-powered")).shouldBe(visible);
    }
}
