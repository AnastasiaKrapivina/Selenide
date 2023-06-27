package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarDate {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now(); // получаем текущую дату
        now = now.plusDays(7);
        Locale localeRu = new Locale("ru", "RU");
        String date = now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(localeRu));

        System.out.println(date);
    }
}

//.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"))
//        String date = now.format(DateTimeFormatter.ofPattern("d"));
//int dayOfMonth = now.getDayOfMonth();
//    int month = now.getMonthValue();
//    int year = now.getYear();