package guru.qa.hw.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarWidgetPage {
    private final SelenideElement fMonth = $("#dateOfBirth").$(".react-datepicker__month-select");
    private final SelenideElement fYear = $("#dateOfBirth").$(".react-datepicker__year-select");
    private final SelenideElement fDate = $("#dateOfBirth").$(".react-datepicker__month");

    public void setBirthday(String year, String month, String date) {
        fYear.selectOption(year);
        fMonth.selectOption(month);
        fDate.find(byText(date)).click();
    }
}
