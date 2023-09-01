package edu.ics111.h10;

/**
 * PairOfDice, randomly generates two integer values to simulate rolling a pair of 
 * 6 sided die when instanced. Includes a roll method that will generate two new values.
 * 
 * <p>Copied from section 5.2.1 in textbook, but I added in a total function
 * 
 * @author David J. Eck
 */
public class PairOfDice {
  public int die1 = (int) (Math.random() * 6) + 1;
  public int die2 = (int) (Math.random() * 6) + 1;

  /**
   * Generates new random values from 1-6 and assigns them to this classes variables.
   */
  public void roll() {
    die1 = (int) (Math.random() * 6) + 1;
    die2 = (int) (Math.random() * 6) + 1;
  }


  /**
   * Returns the sum of the rolled dice.
   * 
   * @return an int sum
   */
  public int total() {
    return die1 + die2;
  }
}