package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator kalkulator;

    @org.junit.jupiter.api.BeforeEach
    private void setUp() {
        kalkulator = new Calculator();
    }
    /**
     *  Testy przestsłyby działać
     *  org.junit.platform.commons.JUnitException: @BeforeAll method 'private void put.io.testing.junit.CalculatorTest.setUp()'
     *  must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
     */

    @org.junit.jupiter.api.Test
    void testAdd() {
        assertTrue(kalkulator.add(2,6)==8);
        assertTrue(kalkulator.add(-3,0)==-3);
    }

    @org.junit.jupiter.api.Test
    void testMultiply() {
        assertTrue(kalkulator.multiply(2,6)==12);
        assertTrue(kalkulator.multiply(-3,0)==0);
    }

    @org.junit.jupiter.api.Test
    void testAddPositiveNumbers() {
        /**
         * assertTrue(kalkulator.addPositiveNumbers(-2,6)==4);
         * IllegalArgumentException: The arguments must be positive
         */
        assertThrows(IllegalArgumentException.class, () -> {kalkulator.addPositiveNumbers(-2,4);});
    }

}