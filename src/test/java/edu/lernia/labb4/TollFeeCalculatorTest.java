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
}
