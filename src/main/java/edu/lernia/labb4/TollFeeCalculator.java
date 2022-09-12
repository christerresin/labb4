package edu.lernia.labb4;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TollFeeCalculator {

    public TollFeeCalculator(String inputFile) {
        String[] dateStrings = createStringArrayOfDates(inputFile);
        // LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
        List<LocalDateTime> dates = new ArrayList<LocalDateTime>(dateStrings.length);
        addFormatedDataToDatesArray(dateStrings, dates);
        Collections.sort(dates);

        ArrayList<LocalDateTime> innerArrayList = new ArrayList<>();
        List<List<LocalDateTime>> dailySortedDates = new ArrayList<>();

        int dateCounter = dates.get(0).getDayOfYear();
        int rowCounter = 0;
        List<Integer> datesByValue = new ArrayList<>();
        List<Integer> indexesOfDates = new ArrayList<>();

        dates.stream().forEachOrdered((date) -> {
            datesByValue.add(date.getDayOfYear());
        });

        int indexCount = 0;

        for (Integer date : datesByValue) {
            if (datesByValue.lastIndexOf(date) == 0) {
                indexesOfDates.add(datesByValue.lastIndexOf(date));
            }

            if (datesByValue.lastIndexOf(date) != indexCount) {
                indexesOfDates.add(datesByValue.lastIndexOf(date));
                indexCount = datesByValue.lastIndexOf(date);
            }
        }

        indexCount = 0;

        for (int index : indexesOfDates) {
            if (index == indexCount) {
                dailySortedDates.add(dates.subList(index, index + 1));
                indexCount++;
            } else {
                dailySortedDates.add(dates.subList(indexCount, index + 1));
                indexCount = index + 1;

            }

        }

        int totalFeeForInputFile = 0;

        for (int i = 0; i < dailySortedDates.size(); i++) {
            List<LocalDateTime> datesArray = dailySortedDates.get(i);
            totalFeeForInputFile += getTotalFeeCost(datesArray);
            System.out.println("Added " + getTotalFeeCost(datesArray) + " to total Sum");
        }

        System.out.println("The total fee for the inputfile is " + totalFeeForInputFile);

    }

    public static int getTotalFeeCost(List<LocalDateTime> dates) {
        int totalFee = 0;
        int intervalRate = 0;
        LocalDateTime intervalStart = dates.get(0);
        for (LocalDateTime date : dates) {
            // System.out.println(date.toString());
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            if (intervalStart == date) {
                totalFee += getTollFeePerPassing(date);
                intervalRate = getTollFeePerPassing(date);
            }
            if (diffInMinutes > 60 && getTollFeePerPassing(date) > 0) {
                totalFee += Math.max(getTollFeePerPassing(date), intervalRate);
                intervalStart = date;
                intervalRate = 0;
            } else {
                intervalRate = getTollFeePerPassing(date);
            }
        }
        return dailyFeeToPay(totalFee);
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date)) {
            return 0;
        }
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

    public static void addFormatedDataToDatesArray(String[] dateStrings, List<LocalDateTime> dates) {
        if (dateStrings.length > 0) {
            for (int i = 0; i < dateStrings.length; i++) {
                dates.add(i, LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        }
    }

    public static String[] createStringArrayOfDates(String inputFile) {
        // using try-with-rescourses to now .close() scanner. "Bug" found in org version
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

    public static int dailyFeeToPay(int totalFee) {
        int maxFee = 60;
        return Math.min(totalFee, maxFee);
    }

    public static void main(String[] args) {
        new TollFeeCalculator("src/test/resources/Lab5.txt");
    }
}
