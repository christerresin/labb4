package edu.lernia.labb4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class TollFeeCalculatorTest {

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
  void checkRateBetweenFifteenThirtyAndSeventeen() {
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 29));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 30));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(15, 59));
    assertEquals(true, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(16, 59));
    assertEquals(false, TollFeeCalculator.rateBetweenFifteenThirtyAndSeventeen(17, 00));
  }

}
