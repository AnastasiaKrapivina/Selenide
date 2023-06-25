package ru.netology.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.opentelemetry.sdk.metrics.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999"); }
    public String generate() {
        LocalDate now = LocalDate.now();
        now = now.plusDays(4);
        String date = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }
    @Test
    void shouldTestCardDeliveryForm() {

        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Новосибирск");
        form.$("[data-test-id=date] input").setValue(generate());
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79999990000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

}
