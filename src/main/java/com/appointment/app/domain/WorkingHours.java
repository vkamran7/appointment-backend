package com.appointment.app.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkingHours {

    private static List<LocalTime> hours = new ArrayList<>();

    static {
        hours.add(LocalTime.of(9, 0));
        hours.add(LocalTime.of(10, 0));
        hours.add(LocalTime.of(11, 0));
        hours.add(LocalTime.of(12, 0));
        hours.add(LocalTime.of(13, 0));
        hours.add(LocalTime.of(14, 0));
        hours.add(LocalTime.of(15, 0));
        hours.add(LocalTime.of(16, 0));
        hours.add(LocalTime.of(17, 0));
    }

    private WorkingHours() {

    }

    public static List<LocalTime> getHours() {
        return hours;
    }

    public boolean isWorkingHour(LocalTime localTime) {
        return hours.contains(localTime);
    }
}
