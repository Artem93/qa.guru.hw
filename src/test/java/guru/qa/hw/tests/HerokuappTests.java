package guru.qa.hw.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;


public class HerokuappTests {

    @BeforeAll
    static void preconditions() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com/";
        Configuration.browserSize = "1920x1280";
    }

    @Test
    void checkDragAndDropTest() {
        open("drag_and_drop");
        $("#column-a").$(byTagAndText("header", "B")).shouldNotBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldNotBe(exist);
        $("#column-a").dragAndDrop(to("#column-b"));
        $("#column-a").$(byTagAndText("header", "B")).shouldBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldBe(exist);
    }

    @Test
    void checkActionsTest() {
        open("drag_and_drop");
        $("#column-a").$(byTagAndText("header", "B")).shouldNotBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldNotBe(exist);

        actions()
                .moveToElement($("#column-a")).clickAndHold()
                .moveToElement($("#column-b")).release().perform();
        $("#column-a").$(byTagAndText("header", "B")).shouldBe(exist);
        $("#column-b").$(byTagAndText("header", "A")).shouldBe(exist);
    }
}
