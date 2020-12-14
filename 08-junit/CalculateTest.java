package put.io.testing.audiobooks;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {

    @org.junit.jupiter.api.Test
    void testSubskrybent() {
        Customer klient = new Customer("Bob", Customer.LoyaltyLevel.STANDARD, true);
        Audiobook audiobook = new Audiobook("Wojna i Pokój", 100.00);
        assertTrue(new AudiobookPriceCalculator().calculate(klient, audiobook) == 0);
    }

    @org.junit.jupiter.api.Test
    void testStandard() {
        Customer klient = new Customer("Bob", Customer.LoyaltyLevel.STANDARD, false);
        Audiobook audiobook = new Audiobook("Wojna i Pokój", 100.00);
        assertTrue(new AudiobookPriceCalculator().calculate(klient, audiobook) == 100);
    }

    @org.junit.jupiter.api.Test
    void testSilver() {
        Customer klient = new Customer("Bob", Customer.LoyaltyLevel.SILVER, false);
        Audiobook audiobook = new Audiobook("Wojna i Pokój", 100.00);
        assertTrue(new AudiobookPriceCalculator().calculate(klient, audiobook) == 90);
    }

    @org.junit.jupiter.api.Test
    void testGold() {
        Customer klient = new Customer("Bob", Customer.LoyaltyLevel.GOLD, false);
        Audiobook audiobook = new Audiobook("Wojna i Pokój", 100.00);
        assertTrue(new AudiobookPriceCalculator().calculate(klient, audiobook) == 80);
    }

}