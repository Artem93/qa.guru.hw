package guru.qa.hw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GitHubPage {

    public static final String softAssertionsConst = "SoftAssertions";
    public static final String softAssertionsCodeHeaderConst = "3. Using JUnit5 extend test class:";
    public static final String solutionsConst = "Solutions";
    public static final String enterpriseConst = "Enterprise";
    public static final String aiPoweredConst = "The AI-powered";
    public static final String[] softAssertionsCodeConst =
            new String[]{"@ExtendWith({SoftAssertsExtension.class})", "class Tests {", "@Test",
                    "void test() {", "Configuration.assertionMode = SOFT;", "open(\"page.html\");",
                    "$(\"#first\").should(visible).click();", "$(\"#second\").should(visible).click();"};

    private static final SelenideElement wikiTab = $("#wiki-tab");
    private static final SelenideElement wikiPagesFilter = $("#wiki-pages-filter");
    private static final SelenideElement wikiPagesBox = $("#wiki-pages-box");
    private static final ElementsCollection headerMenuGitHub = $$(".HeaderMenu-item");

    public GitHubPage wikiTabClick() {
        wikiTab.click();
        return this;
    }

    public GitHubPage setWikiPagesFilterClick(String filter) {
        wikiPagesFilter.setValue(filter);
        return this;
    }

    public GitHubPage findPageInSearchResultsAndClick(String tag) {
        wikiPagesBox.$(byTagAndText("a", tag)).click();
        return this;
    }

    public GitHubPage checkHeader(String header) {
        $(byTagAndText("h1", header)).shouldBe(visible);
        return this;
    }

    public GitHubPage checkTextInLibPage(String text) {
        $(byText(text)).scrollTo().shouldBe(visible);
        return this;
    }

    public GitHubPage checkChildTextForTextInLibPage(String parent, String child) {
        $(byText(parent))
                .parent().sibling(0).find("pre").shouldHave(text(child));
        return this;
    }

    public GitHubPage checkChildrenTextForTextInLibPage(String parent, String[] children) {
        for (var child : children) {
            checkChildTextForTextInLibPage(parent, child);
        }
        return this;
    }

    public GitHubPage hoverOnGitHubMenu(String menuItem) {
        headerMenuGitHub.find(text(String.format(" %s ", menuItem))).hover();
        return this;
    }

    public GitHubPage clickByLink(String textLink) {
        $(byTagAndText("a", textLink)).click();
        return this;
    }
}
