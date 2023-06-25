package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsedDate {
    public String generate() {
        LocalDate now = LocalDate.now();
        now = now.plusDays(4);
        String date = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }
}
