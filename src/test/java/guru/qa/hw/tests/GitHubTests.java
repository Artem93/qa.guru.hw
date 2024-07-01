package guru.qa.hw.tests;

import guru.qa.hw.pages.GitHubPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.hw.pages.GitHubPage.*;


public class GitHubTests extends GitHubBaseTest {

    @Test
    @DisplayName("Проверка блока с кодом на странице SoftAssertions")
    void checkCodeInSoftAssertionsPageTest() {
        open("selenide/selenide");
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage
                .wikiTabClick()
                .setWikiPagesFilterClick(softAssertionsConst)
                .findPageInSearchResultsAndClick(softAssertionsConst)
                .checkHeader(softAssertionsConst)
                .checkTextInLibPage(softAssertionsCodeHeaderConst)
                .checkChildrenTextForTextInLibPage(softAssertionsCodeHeaderConst, softAssertionsCodeConst);
    }


    @CsvSource(
            value = {
                    "SoftAssertions, 3. Using JUnit5 extend test class:",
                    "Safari, Way 2: WebDriverProvider"
            }
    )
    @ParameterizedTest
    @DisplayName("Проверка блока с текстом на странице {0}")
    void checkTextInPageTest(String page, String text) {
        open("selenide/selenide");
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage
                .wikiTabClick()
                .setWikiPagesFilterClick(page)
                .findPageInSearchResultsAndClick(page)
                .checkHeader(page)
                .checkTextInLibPage(text);
    }

    @DisplayName("Проверка перехода на страницу AI через хедер меню github")
    @Test
    void checkPageAiPoweredTest() {
        open("");
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage
                .hoverOnGitHubMenu(solutionsConst)
                .clickByLink(enterpriseConst)
                .checkHeader(aiPoweredConst);
    }
}
