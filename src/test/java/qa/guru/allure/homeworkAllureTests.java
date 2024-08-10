package qa.guru.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class homeworkAllureTests {
    public static final String PETROGRADSKII = "Петроградский";
    public static final String VACCINATIONCOVID19 = "Вакцинация от COVID-19";
    public static final String POLICLINICA34 = ".service-block-1__button.service-mo-1__button[data-lpu-id='133']";

    @Test
    @DisplayName("Вариант с чистым Selenide (с Listener)")
    void gorzdravSearchVaccinationCovid() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://gorzdrav.spb.ru/service-free-schedule");
        $("#freeScheduleLink").click();
        $$(".service-districts-list__item").findBy(text("Петроградский")).click();
        $(".service-block-1__button.service-mo-1__button[data-lpu-id='133']").click();
        $(withText("Вакцинация от COVID-19")).should(exist);
    }

    @Test
    @DisplayName("Вариант с Лямбда шагами через step (name, () -> {})")
    void searchVaccinationCovidWithLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем сайт Здоровье Петербуржца", () -> {
            open("https://gorzdrav.spb.ru/service-free-schedule");
        });
        step("Выбираем район" + PETROGRADSKII, () -> {
            $$(".service-districts-list__item").findBy(text(PETROGRADSKII)).click();
        });
        step("Выбираем медорганизацию" + POLICLINICA34, () -> {
            $(POLICLINICA34).click();
        });
        step("Ищем специальность" + VACCINATIONCOVID19, () -> {
            $(withText(VACCINATIONCOVID19)).should(exist);
        });
    }

    @Test
    @DisplayName("Вариант с шагами через аннотацию @Step")
    void searchVaccinationCovidWebTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebTest steps = new WebTest();
        steps.openGorZdrav();
        steps.searchDistrict(PETROGRADSKII);
        steps.searchClinic(POLICLINICA34);
        steps.searchSpecialty(VACCINATIONCOVID19);
    }
}
