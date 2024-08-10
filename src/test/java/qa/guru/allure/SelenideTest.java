package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    void testIssureSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").click();
        $("[data-target='query-builder.input']").sendKeys("eroshenkoam/allure-example");
        $(".QueryBuilder-InputWrapper").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(exist);
    }

}
