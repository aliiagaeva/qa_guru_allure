package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class WebTest {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищeм репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("[data-target='query-builder.input']").sendKeys(repo);
        $(".QueryBuilder-InputWrapper").submit();
    }

    @Step("Кликаем по ссылке репозиторий {repo}")
    public void clickToRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openTabIssue() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issues с номером {issue}")
    public void checkIssueNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Step("Открываем сайт Здоровье Петербуржца")
    public void openGorZdrav() {
        open("https://gorzdrav.spb.ru/service-free-schedule");
    }

    @Step("Выбираем район {district}")
    public void searchDistrict(String district) {
        $$(".service-districts-list__item").findBy(text(district)).click();
    }

    @Step("Выбираем медорганизацию {clinic}")
    public void searchClinic(String clinic) {
        $(clinic).click();
    }

    @Step("Ищем специальность {specialty}")
    public void searchSpecialty(String specialty) {
        $(withText(specialty)).should(exist);

    }
}
