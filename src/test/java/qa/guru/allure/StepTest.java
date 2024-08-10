package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищeм репозиторий" + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("[data-target='query-builder.input']").sendKeys(REPOSITORY);
            $(".QueryBuilder-InputWrapper").submit();
        });
        step("Кликаем по ссылке репозиторий" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issues с номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebTest steps = new WebTest();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickToRepository(REPOSITORY);
        steps.openTabIssue();
        steps.checkIssueNumber(ISSUE);
    }
}

