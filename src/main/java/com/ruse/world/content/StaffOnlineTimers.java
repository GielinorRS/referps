package com.ruse.world.content;

import java.time.LocalDate;
import java.util.HashMap;

public class StaffOnlineTimers {

    public static HashMap<String, Long> staffTimes = new HashMap<>();
    long epochDay = LocalDate.now().toEpochDay();



}
