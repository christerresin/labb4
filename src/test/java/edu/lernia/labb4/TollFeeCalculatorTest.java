package edu.lernia.labb4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TollFeeCalculatorTest {

  String[] mockDateStrings = { "2020-06-30 00:05", "2020-06-30 10:13", "2020-06-30 10:25", "2020-06-30 11:04",
      "2020-06-30 18:30" };
  LocalDateTime[] mockDates = new LocalDateTime[mockDateStrings.length];
  LocalDateTime[] mockDatesShort = new LocalDateTime[mockDateStrings.length - 1];

  @Test
  @DisplayName("Check if total fee amount is returned when below max fee")
  void checkBelowMaxFee() {
    assertEquals(35, TollFeeCalculator.feeToPay(35));
  }

  @Test
  @DisplayName("Check if max fee amount is return when totalFee is above maxFee")
  void checkAboveMaxFee() {
    assertEquals(60, TollFeeCalculator.feeToPay(75));
  }

  @Test
  void checkRateBetweenSixAndSixThirty() {
    assertEquals(false, TollFeeCalculator.rateBetweenSixAndSixThirty(5, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSixAndSixThirty(6, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSixAndSixThirty(6, 15));
    assertEquals(true, TollFeeCalculator.rateBetweenSixAndSixThirty(6, 29));
    assertEquals(false, TollFeeCalculator.rateBetweenSixAndSixThirty(6, 30));
  }

  @Test
  void checkRateBetweenSixThirtyAndSeven() {
    assertEquals(false, TollFeeCalculator.rateBetweenSixThirtyAndSeven(5, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSixThirtyAndSeven(6, 30));
    assertEquals(true, TollFeeCalculator.rateBetweenSixThirtyAndSeven(6, 45));
    assertEquals(true, TollFeeCalculator.rateBetweenSixThirtyAndSeven(6, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenSixThirtyAndSeven(7, 30));
  }

  @Test
  void checkRateBetweenSevenAndEight() {
    assertEquals(false, TollFeeCalculator.rateBetweenSevenAndEight(5, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSevenAndEight(7, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSevenAndEight(7, 45));
    assertEquals(true, TollFeeCalculator.rateBetweenSevenAndEight(7, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenSevenAndEight(8, 0));
  }

  @Test
  void checkRateBetweenEightAndEightThirty() {
    assertEquals(false, TollFeeCalculator.rateBetweenEightAndEightThirty(7, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenEightAndEightThirty(8, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenEightAndEightThirty(8, 15));
    assertEquals(true, TollFeeCalculator.rateBetweenEightAndEightThirty(8, 29));
    assertEquals(false, TollFeeCalculator.rateBetweenEightAndEightThirty(8, 30));
  }

  @Test
  @DisplayName("Bugg found in erlier logic")
  void checkRateBetweenEightThirtyAndFifteen() {
    assertEquals(false, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(8, 29));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(8, 30));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(8, 45));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(8, 59));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(10, 22));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(14, 22));
    assertEquals(true, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(14, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenEightThirtyAndFifteen(15, 30));
  }

  @Test
  void checkRateBetweenFifteenAndFifteenThirty() {
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenAndFifteenThirty(14, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenAndFifteenThirty(15, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenAndFifteenThirty(15, 15));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenAndFifteenThirty(15, 29));
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenAndFifteenThirty(15, 30));
  }

  @Test
  @DisplayName("Bug found in logic")
  void checkRateBetweenFifteenThirtyAndSeventeen() {
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 29));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 30));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 59));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(16, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(17, 00));
  }

  @Test
  void checkRateBetweenSeventeenAndEighteen() {
    assertEquals(false, TollFeeCalculator.rateBetweenSeventeenAndEighteen(16, 59));
    assertEquals(true, TollFeeCalculator.rateBetweenSeventeenAndEighteen(17, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenSeventeenAndEighteen(17, 29));
    assertEquals(true, TollFeeCalculator.rateBetweenSeventeenAndEighteen(17, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenSeventeenAndEighteen(18, 0));
  }

  @Test
  void checkRateBetweenEighteenAndEighteenThirty() {
    assertEquals(false, TollFeeCalculator.rateBetweenEighteenAndEighteenThirty(17, 59));
    assertEquals(true, TollFeeCalculator.rateBetweenEighteenAndEighteenThirty(18, 0));
    assertEquals(true, TollFeeCalculator.rateBetweenEighteenAndEighteenThirty(18, 15));
    assertEquals(true, TollFeeCalculator.rateBetweenEighteenAndEighteenThirty(18, 29));
    assertEquals(false, TollFeeCalculator.rateBetweenEighteenAndEighteenThirty(18, 30));
  }

  @Test
  void checkTollFreeHours() {
    for (int i = 0; i < mockDates.length; i++) {
      mockDates[i] = LocalDateTime.parse(mockDateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    assertEquals(0, TollFeeCalculator.getTollFeePerPassing(mockDates[0]));
    assertEquals(0, TollFeeCalculator.getTollFeePerPassing(mockDates[4]));
  }

  @Test
  @DisplayName("Bug found, length for mockDates (dates) was calculated with -1")
  void compareInputArrayAndGeneratedArrayLengths() {
    assertTrue(TollFeeCalculator.allDatesIncluded(mockDateStrings, mockDates));
    assertFalse(TollFeeCalculator.allDatesIncluded(mockDateStrings, mockDatesShort));
  }

  @Test
  @DisplayName("Bug found, intervals logic was returning wrong amounts. The interval value even if passing during free or lower fee hour")
  void checkOneHourTollFeeInterval() {
    for (int i = 0; i < mockDates.length; i++) {
      mockDates[i] = LocalDateTime.parse(mockDateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    assertEquals(8, TollFeeCalculator.getTotalFeeCost(mockDates));
  }

}
