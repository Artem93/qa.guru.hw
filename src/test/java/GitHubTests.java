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
    static void Precondition() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1280";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkSoftAssertions() {
        open("selenide/selenide");

        // Открываем таб
        $("#wiki-tab").click();

        // Раскрываем весь список страниц
        $(".wiki-more-pages-link button").click();

        // Выбираем нужную и кликаем
        $$("[data-filterable-for='wiki-pages-filter'] li")
                .findBy(text("SoftAssertions")).find("a").click();

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
}
