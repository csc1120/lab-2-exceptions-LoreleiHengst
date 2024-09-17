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
import java.util.Arrays;
import java.util.Scanner;

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
        System.out.println(Arrays.toString(rolledDice));


    }

    private static int[] getInput() throws FileNotFoundException {
        Path inputReader = Paths.get("reader", "reader.txt");
        File f = inputReader.toFile();
        Scanner scan = new Scanner(f);
        Scanner read = new Scanner(System.in);
        int[] arr = new int[3];
        try {
            PrintWriter pw = new PrintWriter(f);
            System.out.println("Enter configuration:");
            String readStr = read.nextLine();
            pw.print(readStr);
            pw.flush();

            int int1 = scan.nextInt();
            int int2 = scan.nextInt();
            int int3 = scan.nextInt();
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
    private static int[] rollDice(Die[] dice, int numSides, int numRolls){
        int[] frequencies = new int[(numSides - 1) * dice.length + 1];
        int sum = 0;
        for(int i = 0; i<=numRolls; i++) {
            for (Die die : dice) {
                die.roll();
                sum += die.getCurrentValue();
            }
            if(sum>= dice.length){
                frequencies[sum - dice.length]++;
            }
            sum = 0;
        }
        return frequencies;
    }

}