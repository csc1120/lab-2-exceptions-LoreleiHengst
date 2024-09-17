/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Lorelei
 * Last Updated: 09/15/24
 */
package hengstl;

import java.util.Random;

/**
 * Creates the class die where the constructors are used
 * it holds the methods used in the main driver
 */
public class Die {
    private int currentValue;
    private int numSides;
    private final Random random = new Random();

    /**
     * creates an instance of Die
     * @param numSides gets the number of sides the die will have
     */
    public Die(int numSides) {
        final int maxSides = 100;
        final int minSides = 2;
        if(numSides >= minSides && numSides <= maxSides) {
            this.numSides = numSides;
        }
    }

    /**
     * rolls the dice and changes the value.
     * can only be accessed by reaching the .getCurrentValue() method
     */
    public void roll(){
        currentValue = random.nextInt(numSides)+1;
    }

    /**
     * Gets the current value when needed and changes that value to zero.
     * @return int
     * @throws DieNotRolledException which makes sure that the die is rolled
     */
    public int getCurrentValue() throws DieNotRolledException {
        int holder;
        if(currentValue==0){
            throw new DieNotRolledException("Die not rolled");
        } else{
            holder = currentValue;
        }
        currentValue = 0;
        return holder;
    }
}