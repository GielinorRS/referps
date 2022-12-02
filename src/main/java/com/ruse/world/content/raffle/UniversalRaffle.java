package com.ruse.world.content.raffle;

import com.ruse.util.Misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.*;

public class UniversalRaffle {
    public static int endDay = (int) (LocalDate.now().toEpochDay() + 7);

    public static void setEndDay(int epochDay) {
        endDay = epochDay;
    }



    public static String getDate() {
        Date getTime = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(getTime);
    }

    public static void run() {
        if (LocalDate.now().toEpochDay() == UniversalRaffle.endDay) {
            UniversalRaffle.endDay += 7;
            PerksRaffle.drawRaffle();
            VotingRaffle.drawRaffle();
            DonationRaffle.drawRaffle();
        }
    }

    public static String getTimeTillDraw() {
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.ofEpochDay(endDay);

        Period period = Period.between(from, to);
        if (period.getDays() <= 1) {
            return Misc.getTimeTillReset();
        }

        ZonedDateTime time = ZonedDateTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();

        if (minute > 0) {
            hour = 24 - hour - 1;
            minute = 60 - minute;
        } else {
            hour = 24 - hour;
            minute = 0;
        }

        String hourPrefix = hour + "";
        String minutePrefix = minute + "";

        return period.getDays() + " day" + (period.getDays() > 1 ? "s" : "")  + " " + hourPrefix + "h " ;
    }


    public static ArrayList<String> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        ArrayList<String> sorted = new ArrayList<>();

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            sorted.add(aa.getKey());
            //temp.put(aa.getKey(), aa.getValue());
        }

        return sorted;
    }

}
