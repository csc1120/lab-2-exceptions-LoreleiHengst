/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Lorelei
 * Last Updated: 09/15/24
 */
package hengstl;

import java.util.Random;

public class Die {
    private int currentValue;
    private int numSides;
    private final int MAX_SIDES = 100;
    private final int MIN_SIDES = 2;
    private Random random;
    public Die(int numSides) {
        if(numSides >= MIN_SIDES && numSides <= MAX_SIDES) {
            this.numSides = numSides;
        }
    }
    public void roll(){
        currentValue = random.nextInt(numSides)+1;
    }
    public int getCurrentValue(){
        int holder=0;
        if(currentValue>=2 && currentValue<=numSides) {
            holder = currentValue;
        }
        else{
            //dienotrolledexception
        }
        currentValue=0;
        return holder;
    }
}