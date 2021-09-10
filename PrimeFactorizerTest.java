package test.rice;

import main.rice.PrimeFactorizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A test suite for the PrimeFactorizer class. Every method with the annotation "@Test"
 * will be called when running the test with JUnit.
 */
public class PrimeFactorizerTest {

    /**
     * A prime factorizer with an upper bound of 100.
     */
    private static PrimeFactorizer factorizer100 = new PrimeFactorizer(100);
    private static PrimeFactorizer factorizer30000 = new PrimeFactorizer(30000);

    /**
     * Tests that attempting factorization of a negative number rightfully returns null.
     */
    @Test
    void testFactorizeNegative() {
        int[] actual = factorizer100.computePrimeFactorization(-1);
        assertNull(actual);
    }

    /**
     * Tests that attempting factorization of 0 rightfully returns null.
     */
    @Test
    void testFactorize0() {
        int[] actual = factorizer100.computePrimeFactorization(0);
        assertNull(actual);
    }

    /**
     * Tests that attempting factorization of 1 rightfully returns null.
     */
    @Test
    void testFactorize1() {
        int[] actual = factorizer100.computePrimeFactorization(1);
        assertNull(actual);
    }

    /**
     * Tests factorization of a prime that can be factorized.
     */
    @Test
    void testFactorize7() {
        int[] actual = factorizer100.computePrimeFactorization(7);
        int[] expected = new int[]{7};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a prime that can be factorized.
     */
    @Test
    void testFactorize2() {
        int[] actual = factorizer100.computePrimeFactorization(2);
        int[] expected = new int[]{2};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a prime that can be factorized.
     */
    @Test
    void testFactorizePrime97() {
        int[] actual = factorizer100.computePrimeFactorization(97);
        int[] expected = new int[]{97};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a composite that can be factorized
     */
    @Test
    void testFactorizePrime100() {
        int[] actual = factorizer100.computePrimeFactorization(100);
        int[] expected = new int[]{2, 2, 5, 5};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a composite that can be factorized.
     */
    @Test
    void testFactorizePrime99() {
        int[] actual = factorizer100.computePrimeFactorization(99);
        int[] expected = new int[]{3, 3, 11};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a composite that can be factorized.
     */
    @Test
    void testFactorizePrime30() {
        int[] actual = factorizer100.computePrimeFactorization(30);
        int[] expected = new int[]{2, 3, 5};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of number includes two distinct primes.
     */
    @Test
    void testTwoDistinctPrimes() {
        int[] actual = factorizer100.computePrimeFactorization(6);
        int[] expected = new int[]{2, 3};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a number that includes three distinct primes.
     */
    @Test
    void testThreeDistinctPrimes() {
        int[] actual = factorizer100.computePrimeFactorization(90);
        int[] expected = new int[]{2, 3, 3, 5};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a number with a prime that repeats three times.
     */
    @Test
    void testRepeat2() {
        int[] actual = factorizer100.computePrimeFactorization(24);
        int[] expected = new int[]{2, 2, 2, 3};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a number with a prime that repeats twice.
     */
    @Test
    void testRepeat5() {
        int[] actual = factorizer100.computePrimeFactorization(50);
        int[] expected = new int[]{2, 5, 5};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a prime that is greater than maxNumToFactorize and too large to be factorized.
     */
    @Test
    void testPrimeGreaterThanMax() {
        int[] actual = factorizer100.computePrimeFactorization(101);
        assertNull(actual);
    }

    /**
     * Tests factorization of a composite that is greater than maxNumToFactorize and too large to be factorized.
     */
    @Test
    void testCompositeGreaterThanMax() {
        int[] actual = factorizer100.computePrimeFactorization(102);
        assertNull(actual);
    }

    /**
     * Tests factorization of a prime that is very large but less than maxNumToFactorize and can be factorized.
     */
    @Test
    void testPrimeLargeNumber21017() {
        int[] actual = factorizer30000.computePrimeFactorization(21017);
        int[] expected = new int[]{21017};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a prime that is very large but less than maxNumToFactorize can be factorized.
     */
    @Test
    void testPrimeLargeNumber29959() {
        int[] actual = factorizer30000.computePrimeFactorization(29959);
        int[] expected = new int[]{29959};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a composite that is very large but less than maxNumToFactorize can be factorized.
     */
    @Test
    void testCompositeLargeNumberInPrimeList() {
        int[] actual = factorizer30000.computePrimeFactorization(27853);
        int[] expected = new int[]{7, 23, 173};
        assertArrayEquals(expected, actual);
    }
}
