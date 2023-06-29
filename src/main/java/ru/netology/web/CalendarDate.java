package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarDate {
        public static void main(String[] args) {
                        int days = 3;
//                        String pattern = "dd MMMM yyyy";
            String pattern = "d";
        System.out.println(LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern, new Locale("RU"))));
    }
}
//   public static void main(String[] args) {
//        LocalDate now = LocalDate.now(); // получаем текущую дату
//        now = now.plusDays(7);
//        int month = now.getMonthValue();
//        int year = now.getYear();
//        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
//        String monthEnd = monthNames[month-1];
//
//        System.out.println(monthEnd+" "+ year);
//    }
//}
//.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"))
//        String date = now.format(DateTimeFormatter.ofPattern("d"));
//int dayOfMonth = now.getDayOfMonth();
//    int month = now.getMonthValue();
//    int year = now.getYear();