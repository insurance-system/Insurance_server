package com.example.insuranceSystem.global.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDateTime strToDate(String strDate){//strDate 는 "2021-06-19 17:50"와 같은 형식으로 들어옴
        String dateStr = "2021-06-19 17:50";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static String dateToStr(LocalDateTime  localDateTime){//"2022-11-12"와 같은 형식으로 반환해준다.
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
    }
}