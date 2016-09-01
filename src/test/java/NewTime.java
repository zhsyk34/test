import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class NewTime {

    @Test
    public void one() {
        LocalDate date = LocalDate.of(2014, Month.JUNE, 10);
        int year = date.getYear(); // 2014
        Month month = date.getMonth(); // 6月
        int day = date.getDayOfMonth(); // 10
        DayOfWeek week = date.getDayOfWeek(); // 星期二
        int len = date.lengthOfMonth(); // 30 （6月份的天数）
        boolean leap = date.isLeapYear(); // false （不是闰年）

        System.out.println(date);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(week);
        System.out.println(len);
        System.out.println(leap);
        date = date.withYear(2015); // 2015-06-10
        System.out.println(date);
        date = date.plusMonths(2); // 2015-08-10
        System.out.println(date);
        date = date.minusDays(1); // 2015-08-09
        System.out.println(date);
    }

    @Test
    public void two() {
        LocalTime time = LocalTime.of(20, 30, 15);
        int hour = time.getHour(); // 20
        int minute = time.getMinute(); // 30

        System.out.println(time);
        System.out.println(hour);
        System.out.println(minute);
        time = time.withSecond(6); // 20:30:06
        System.out.println(time);
        time = time.plusMinutes(3); // 20:33:06
        System.out.println(time);
        time = time.plusSeconds(30);
        System.out.println(time);
    }

    @Test
    public void four() {
        Period sixMonths = Period.ofMonths(6);
        System.out.println(sixMonths);
        LocalDate date = LocalDate.now();
        LocalDate future = date.plus(sixMonths);
        System.out.println(date);
        System.out.println(future);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse("24/06/2014", f);
        System.out.println(d1);
        String str = date.format(f);
        System.out.println(str);
    }
}
