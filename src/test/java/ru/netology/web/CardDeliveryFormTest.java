package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.opentelemetry.sdk.metrics.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    public String generateDate(int days, String pattern) {  // вспомогательный метод к задаче 1 и 2

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern, new Locale("RU")));
    }
    @Test
        // Тест к задаче 1
    void shouldTestCardDeliveryForm() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Новосибирск");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(generateDate(4, "dd.MM.yyyy"));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4, "dd.MM.yyyy")), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
        // Тест к задаче 2
    void shouldTestCityFirstElement() {// Тест к задаче 2, поиск элемента в коллекции

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("но");
        $$(".menu-item__control").find(text("Великий Новгород")).click();
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] .icon-button").click();
        if (!(generateDate(3, "MM.yyyy")).equals(generateDate(7, "MM.yyyy")))
        {
            $$(".calendar__arrow_direction_right").last().click();
        }
        $$(".calendar__day").find(exactText(generateDate(7, "d"))).click();
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(7, "dd.MM.yyyy")), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}


