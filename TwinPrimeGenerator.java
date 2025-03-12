/**
 * The TwinPrimeGenerator class provides a method to generate a twin prime number
 * within a specified range. A twin prime is a prime number that is 2 away from another prime.
 * 
 * @author Jeremiah Robinson
 */
public class TwinPrimeGenerator {

    /**
     * Generates a twin prime number within the specified range [min, max].
     * This method finds the smallest set of twin primes in the given range 
     * and returns the larger of the two.
     *
     * @param min the minimum value of the range (inclusive)
     * @param max the maximum value of the range (inclusive)
     * @return the larger prime in the smallest set of twin primes found in the range
     * @throws IllegalArgumentException if no twin primes are found in the given range
     */
    public static int generateTwinPrime(int min, int max) {
        for (int i = min; i <= max - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2;
            }
        }
        throw new IllegalArgumentException("No twin primes found in the given range.");
    }

    /**
     * Checks if a given number is prime.
     *
     * @param num the number to check
     * @return true if the number is prime, false otherwise
     */
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}