package main.rice;

/**
 * This class implements a relatively simple algorithm for computing the prime factors
 * of a number.  At initialization, a list of primes is computed. Given a number, this
 * list is used to efficiently compute the prime factors of that number.
 */
public class PrimeFactorizer {

    /**
     * Array of valid prime numbers to test for prime factorization
     */
    private int[] allPrimes;

    /**
     * Upper bound of numbers you can factorize
     */
    private int maxNumToFactorize;

    /**
     * This constructor creates a PrimeFactorizer object that contains an array of all primes.
     * up to the square root of the maxNumToFactorize.
     * @param maxNumToFactorize the upper bound of numbers you can factorize
     */
    public PrimeFactorizer(int maxNumToFactorize) {
        this.maxNumToFactorize = maxNumToFactorize;
        this.allPrimes = populateValidPrimes(maxNumToFactorize);
    }

    /**
     * This method computes the prime factorization of numToFactorize.
     * @param numToFactorize is an int to compute a prime factorization for
     * @return an int array that contains the prime factorization of numToFactorize
     * in ascending order
     */
    public int[] computePrimeFactorization(int numToFactorize) {

        // if the input number is less than 2 or greater than maxNumToFactorize, you should return null
        if (numToFactorize < 2 || numToFactorize > this.maxNumToFactorize){
            return null;
        }

        PrimeFactorizer primes = new PrimeFactorizer(this.maxNumToFactorize);

        // upper bound of the possible number of factors in the prime factorization
        int maxPossibleCountOfPrimes = (int) (Math.log(numToFactorize) / Math.log(2)) + 1;
        int[] tempPrimes = new int[maxPossibleCountOfPrimes];

        int primeFactorsCount = 0;
        for(int testPrime:primes.allPrimes){

            // if divisible by a certain prime number continue dividing by that prime
            while(numToFactorize % testPrime == 0){
                tempPrimes[primeFactorsCount] = testPrime;
                numToFactorize /= testPrime;
                primeFactorsCount += 1;
            }
        }

        // adds the final prime factor if the initial numToFactorize exceeds the square root of the numToFactorize
        if (numToFactorize != 1) {
            tempPrimes[primeFactorsCount] = numToFactorize;
            primeFactorsCount += 1;
        }

        // populate new array with factors in prime factorization
        int[] primeFactorization = new int[primeFactorsCount];
        for(int i = 0; i < primeFactorsCount; i++){
            primeFactorization[i] = tempPrimes[i];
        }
        return primeFactorization;
    }

    /**
     * This method populates an int array with a list of valid primes up to
     * the square root of the maximum number to factorize.
     *
     * @param maxNumToFactorize maximum number that can be factorized
     * @return an int array that contains valid primes up to the square root of the max number to factorize
     */
    private int[] populateValidPrimes(int maxNumToFactorize){
        int maxPrimeCandidate = (int) Math.ceil(Math.sqrt(maxNumToFactorize));
        int[] primeCandidates = populatePrimeCandidates(maxPrimeCandidate);

        // marks invalid candidates with -1
        int primeCount = markInvalidPrimeCandidates(maxPrimeCandidate, primeCandidates);
        int[] validPrimes = enterValidPrimes(primeCount, primeCandidates);
        return validPrimes;
    }

    /**
     * This method creates an array of valid primes from an array with marked, invalid prime candidates.
     * @param primeCount is an int representing the number of valid primes
     * @param primeCandidates is an int[] array of primeCandidates with invalid prime candidates marked with -1
     * @return an int[] array of valid primes with no extra space
     */
    private int[] enterValidPrimes(int primeCount, int[] primeCandidates){
        int[] validPrimes = new int[primeCount];
        int addedPrimes = 0;
        for(int prime:primeCandidates){

            // populate a new array with only valid primes
            if(addedPrimes != primeCount && prime != -1){
                validPrimes[addedPrimes] = prime;
                addedPrimes += 1;
            }
        }
        return validPrimes;
    }

    /**
     * This method creates an array of consecutive integers that could be potential primes.
     * @param maxPrimeCandidate the maximum int number that could be a prime candidate
     * @return an int[] array with the consecutive integers from 2 to the maxPrimeCandidate
     */
    private int[] populatePrimeCandidates(int maxPrimeCandidate){
        int[] primeCandidates = new int[maxPrimeCandidate - 1];
        for(int i = 2; i <= maxPrimeCandidate; i++){
            primeCandidates[i - 2] = i;
        }
        return primeCandidates;
    }

    /**
     * This method replaces all composite numbers in primeCandidates with -1 and returns the number of valid primes
     * @param maxPrimeCandidate the maximum int number that could be a prime candidate
     * @param primeCandidates int[] array of consecutive integers from 2 to maxPrimeCandidate
     * @return the number of valid primes
     */
    private int markInvalidPrimeCandidates(int maxPrimeCandidate, int[] primeCandidates) {
        int primeCount = 0;

        //Check every factor with the Sieve of Eratosthenes
        for (int i = 0; i < maxPrimeCandidate - 1; i++){
            int testFactor = primeCandidates[i];
            if (testFactor != -1){
                primeCount += 1;

                // Check if every number following the current factor can evenly divide the current factor
                for (int j = i + 1; j < maxPrimeCandidate - 1; j++){
                    int potentialPrime = primeCandidates[j];
                    if (potentialPrime == -1){
                        continue;
                    }

                    // marks invalid primes with -1
                    if (potentialPrime % testFactor == 0){
                        primeCandidates[j] = -1;
                    }
                }
            }
        }
        return primeCount;
    }
}
