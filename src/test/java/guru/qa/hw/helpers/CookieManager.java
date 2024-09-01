package guru.qa.hw.helpers;

import com.codeborne.selenide.WebDriverRunner;

public class CookieManager {
    public static String getCookieValue(String value) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(value));
        return cookieValue.substring(cookieValue.indexOf("=") + 1, cookieValue.indexOf(";"));
    }
}
