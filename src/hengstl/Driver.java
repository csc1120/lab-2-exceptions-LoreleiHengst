/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Lorelei
 * Last Updated: 9/12/2024
 */
package hengstl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Holds all the methods used with the main class
 * and methods that call on the die class
 */
public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Please enter the number of dice to roll, ");
        System.out.print("how many sides the dice have, ");
        System.out.println("and how many rolls to complete, separating the values by a space.");
        System.out.println("Example: \"2 6 1000\"");
        System.out.println();
        int[] arr2 = Driver.getInput();
        Die[] dice = Driver.createDice(arr2[0], arr2[1]);
        int[] rolledDice = Driver.rollDice(dice, arr2[1], arr2[2]);
        Driver.report(dice.length, rolledDice, Driver.findMax(rolledDice));


    }

    private static int[] getInput() throws FileNotFoundException {
        Path inputReader = Paths.get("reader", "reader.txt");
        File f = inputReader.toFile();
        Scanner scan = new Scanner(f);
        Scanner read = new Scanner(System.in);
        int[] arr = new int[3];
        try {
            try (PrintWriter pw = new PrintWriter(f)) {
                System.out.print("Enter configuration:");
                String readStr = read.nextLine();
                pw.print(readStr);
                pw.flush();
            }
            int int1;
            try{
                int1 = scan.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Invalid input: All values must be whole numbers.");
                return Driver.getInput();
            } catch(NoSuchElementException e){
                System.out.println("Invalid input: Expected 3 values but only received 0");
                return Driver.getInput();
            }
            int int2;
            try{
                int2 = scan.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Invalid input: All values must be whole numbers.");
                return Driver.getInput();
            } catch(NoSuchElementException e){
                System.out.println("Invalid input: Expected 3 values but only received 1");
                return Driver.getInput();
            }
            int int3;
            try{
                int3 = scan.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Invalid input: All values must be whole numbers.");
                return Driver.getInput();
            } catch(NoSuchElementException e){
                System.out.println("Invalid input: Expected 3 values but only received 2");
                return Driver.getInput();
            }
            arr[0] = int1;
            arr[1] = int2;
            arr[2] = int3;
            return arr;
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    private static Die[] createDice(int numDice, int numSides){
        Die[] holdDice = new Die[numDice];
        for(int i = 0; i<numDice; i++) {
            holdDice[i] = new Die(numSides);
        }
        return holdDice;
    }
    private static int[] rollDice(Die[] dice, int numSides, int numRolls)
            throws FileNotFoundException {
        int[] frequencies = new int[(numSides - 1) * dice.length + 1];
        int sum = 0;
        for(int i = 0; i<=numRolls; i++) {
            for (Die die : dice) {
                try {
                    die.roll();
                    sum += die.getCurrentValue();
                } catch(IllegalArgumentException e){
                    System.out.println("Bad die creation: Illegal number of sides: " +numSides);
                    int[] arrHolder = Driver.getInput();
                    Die[] dice1 = Driver.createDice(arrHolder[0], arrHolder[1]);
                    return Driver.rollDice(dice1, arrHolder[1], arrHolder[2]);
                }
            }
            if(sum>= dice.length){
                frequencies[sum - dice.length]++;
            }
            sum = 0;
        }
        return frequencies;
    }
    private static int findMax(int[] rolls){
        int holder = Integer.MIN_VALUE;
        for (int roll : rolls) {
            if (holder < roll) {
                holder = roll;
            }
        }
        return holder;
    }
    private static void report(int numDice, int[] rolls, int max){
        String[] starHolder = new String[rolls.length];
        final int percent = 10;
        int scale = max / percent;
        for(int i = 0; i< starHolder.length; i++) {
            int numStars = rolls[i] / scale;
            starHolder[i] = "*".repeat(Math.max(0, numStars));
        }
        for(int i = numDice; i<rolls.length+numDice; i++) {
            System.out.printf("%-2d:%-9d%s\n", i, rolls[i-numDice], starHolder[i-numDice]);
        }
    }
}