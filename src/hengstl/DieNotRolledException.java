/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Lorelei
 * Last Updated: 09/17/24
 */
package hengstl;

/**
 * the class which holds the constructor for DieNotRolledException
 */
public class DieNotRolledException extends Exception {
    /**
     * creates the exception
     * @param message which can be accessed later
     */
    public DieNotRolledException(String message) {
        super(message);
    }
}
