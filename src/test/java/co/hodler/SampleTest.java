package co.hodler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {
  @Test
  @DisplayName("My first test")
  void myFirstTest() {
    assertEquals(2, 1 + 1);
  }
}
