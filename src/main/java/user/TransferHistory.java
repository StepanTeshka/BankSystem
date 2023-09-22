package user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class TransferHistory {
    private double value;
    private long toUserNumber;
    private ZoneId zone;
    private LocalDate date;
    private LocalTime time;
    private DayOfWeek day;

    private boolean type;

    public TransferHistory(double value, long toUserNumber, LocalDate date, LocalTime time, DayOfWeek day, boolean type) {
        this.value = value;
        this.toUserNumber = toUserNumber;
        this.date = date;
        this.time = time;
        this.day = day;
        this.type = type;
    }

    public void printInfo(){
        if(type) {
            System.out.printf("Перевод \n кому %s \n время %s \n дата %s \n день недели %s \n страна %s \n сумма %s \n", toUserNumber, time, date, day, zone, value);
        } else System.out.printf("Поступление \n от кого %s \n время %s \n дата %s \n день недели %s \n страна %s \n сумма %s \n", toUserNumber, time, date, day, zone, value);
    }
}

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
