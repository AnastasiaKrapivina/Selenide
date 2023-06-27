package ru.netology.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.opentelemetry.sdk.metrics.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    public String generate() { // вспомогательный метод к задаче 1
        LocalDate now = LocalDate.now();
        now = now.plusDays(4);
        String date = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public String calendarDay() { // вспомогательный метод к задаче 2, генерирует выбор дня на неделю вперёд
        LocalDate now = LocalDate.now(); // получаем текущую дату
        now = now.plusDays(7);
        String date = now.format(DateTimeFormatter.ofPattern("d"));
        int dayOfMonth = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return date; // число
    }
//   Вспомогательный метод к задаче 2,
//   генерирует месяц и год для выбора даты на неделю вперед, не работает русская локаль.
//    public String monthAndYear() {//
//        LocalDate now = LocalDate.now(); // получаем текущую дату
//        now = now.plusDays(7);
//        Locale localeRu = new Locale("ru", "RU");
//        String date = now.format(DateTimeFormatter.ofPattern("MMMM yyyy").withLocale(localeRu));
//        return date; // месяц и год
//    }

    @Test
        // Тест к задаче 1
    void shouldTestCardDeliveryForm() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Новосибирск");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(generate());
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
        // Тест к задаче 2. не получилось сделать сравнение месяца и года в календаре
        // с генерируемым значением. Так же не получилось применить if else....
    void shouldTestCityLastElementAndCalendar() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("но");
        $$(".menu-item__control").last().click();
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] .icon-button").click();
        $$(".calendar__arrow_direction_right").last().click();
        $$(".calendar__day").find(exactText(calendarDay())).click();
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
        // Тест к задаче 2, выбирает последний город из списка
    void shouldTestCitySecondElement() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("но");
        $(byText("Краснодар")).click();
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] .icon-button").click();
    }

    @Test
        // Тест к задаче 2
    void shouldTestCityFirstElement() {// Тест к задаче 2, поиск элемента в коллекции

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("но");
        $$(".menu-item__control").find(text("Великий Новгород")).click();
    }
}


//        $(".calendar__name").shouldNotHave(text(monthAndYear()));
//        $(byText(monthAndYear())).shouldNotBe(visible);
//        if($(".calendar__name").shouldNotHave(text(monthAndYear()))) {
//            $$(".calendar__arrow_direction_right").last().click();
//            $$(".calendar__day").find(exactText(calendarDay())).click();
//        } else {
//            $$(".calendar__day").find(exactText(calendarDay())).click();
//        }


