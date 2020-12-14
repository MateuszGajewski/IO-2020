package put.io.testing.junit;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(7, 6);
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertTrue(10/0==11);
    }

    /**
     *  test1 - Failure - naruszenie asercji
     *  test2 - Error   - nieoczekiwany wyjątek
     */

    @org.junit.jupiter.api.Test
    public void test3() {
        try {
            assertTrue(10==11);
        }
        catch (org.opentest4j.AssertionFailedError e){}
    }

}