package edu.lernia.labb4;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TollFeeCalculator {

    public TollFeeCalculator(String inputFile) {
        String[] dateStrings = createStringArrayOfDates(inputFile);
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
        createLDTArrayOfDates(dateStrings, dates);
        System.out.println("The total fee for the inputfile is " + getTotalFeeCost(dates));
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        int intervalRate = 0;
        LocalDateTime intervalStart = dates[0];
        for (LocalDateTime date : dates) {
            System.out.println(date.toString());
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            if (diffInMinutes > 60 && getTollFeePerPassing(date) > 0) {
                totalFee += Math.max(getTollFeePerPassing(date), intervalRate);
                intervalStart = date;
                intervalRate = 0;
            } else {
                intervalRate = getTollFeePerPassing(date);
            }
        }
        return feeToPay(totalFee);
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date))
            return 0;
        int hour = date.getHour();
        int minute = date.getMinute();
        if (rateBetweenSixAndSixThirty(hour, minute))
            return 8;
        else if (rateBetweenSixThirtyAndSeven(hour, minute))
            return 13;
        else if (rateBetweenSevenAndEight(hour, minute))
            return 18;
        else if (rateBetweenEightAndEightThirty(hour, minute))
            return 13;
        else if (rateBetweenEightThirtyAndFifteen(hour, minute))
            return 8;
        else if (rateBetweenFifteenAndFifteenThirty(hour, minute))
            return 13;
        else if (rateBetweenFifteenThirtyAndSeventeen(hour, minute))
            return 18;
        else if (rateBetweenSeventeenAndEighteen(hour, minute))
            return 13;
        else if (rateBetweenEighteenAndEighteenThirty(hour, minute))
            return 8;
        else
            return 0;
    }

    public static void createLDTArrayOfDates(String[] dateStrings, LocalDateTime[] dates) {
        if (isMatchingArrayOfDates(dateStrings, dates)) {
            for (int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
        }
    }

    public static String[] createStringArrayOfDates(String inputFile) {
        try (Scanner sc = new Scanner(new File(inputFile))) {
            while (sc.hasNextLine()) {
                String[] dateStrings = sc.nextLine().split(", ");
                return dateStrings;
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Could not read file " + new File(inputFile).getAbsolutePath());
        }
        return null;
    }

    public static boolean isMatchingArrayOfDates(String[] dateStrings, LocalDateTime[] dates) {
        return dateStrings.length == dates.length && dateStrings.length > 0 && dates.length > 0;
    }

    public static boolean rateBetweenEighteenAndEighteenThirty(int hour, int minute) {
        return hour == 18 && minute >= 0 && minute <= 29;
    }

    public static boolean rateBetweenSeventeenAndEighteen(int hour, int minute) {
        return hour == 17 && minute >= 0 && minute <= 59;
    }

    public static boolean rateBetweenFifteenThirtyAndSeventeen(int hour, int minute) {
        if (hour == 15 && minute >= 30 && minute <= 59) {
            return true;
        } else if (hour == 16 && minute >= 0 && minute <= 59) {
            return true;
        }
        return false;
    }

    public static boolean rateBetweenFifteenAndFifteenThirty(int hour, int minute) {
        return hour == 15 && minute >= 0 && minute <= 29;
    }

    public static boolean rateBetweenEightThirtyAndFifteen(int hour, int minute) {
        if (hour == 8 && minute >= 30 && minute <= 59) {
            return true;
        } else if (hour >= 9 && hour <= 14 && minute >= 0 && minute <= 59) {
            return true;
        }
        return false;
    }

    public static boolean rateBetweenEightAndEightThirty(int hour, int minute) {
        return hour == 8 && minute >= 0 && minute <= 29;
    }

    public static boolean rateBetweenSevenAndEight(int hour, int minute) {
        return hour == 7 && minute >= 0 && minute <= 59;
    }

    public static boolean rateBetweenSixThirtyAndSeven(int hour, int minute) {
        return hour == 6 && minute >= 30 && minute <= 59;
    }

    public static boolean rateBetweenSixAndSixThirty(int hour, int minute) {
        return hour == 6 && minute >= 0 && minute <= 29;
    }

    public static boolean isTollFreeDate(LocalDateTime date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7
                || date.getMonth().getValue() == 7;
    }

    public static int feeToPay(int totalFee) {
        int maxFee = 60;
        return Math.min(totalFee, maxFee);
    }

    public static void main(String[] args) {
        new TollFeeCalculator("src/test/resources/Lab4.txt");
    }
}
