package edu.ics111.h10;

/**
 * A simple player class with a name and score.
 * 
 * @author Eric Zhou
 */
public class Player {
  private String name;
  private int score;
  private static int playerCount = 1;
  private StringBuilder sb = new StringBuilder();

  /**
   * Constructor with no parameter. Will assign default values.
   */
  public Player() {
    sb.append("Player ");
    name = sb.append(playerCount).toString();
    score = 0;
    playerCount++;
  }


  /**
   * Constructor with a parameter to set name.
   * 
   * @param iniName a String for name
   */
  public Player(String iniName) {
    name = iniName;
    score = 0;
    playerCount++;
  }


  /**
   * Gets the name variable from this instance.
   * 
   * @return a name String
   */
  public String getName() {
    return name;
  }


  /**
   * Sets the name variable of this instance.
   * 
   * @param newName a String to assign
   */
  public void setName(String newName) {
    name = newName;
  }


  /**
   * Gets the score variable from this instance.
   * 
   * @return an integer score
   */
  public int getScore() {
    return score;
  }
  
  /**
   * Gets the score variable from this instance as a string.
   * 
   * @return the score as a string
   */
  public String getScoreString() {
    return String.valueOf(score);
  }


  /**
   * Sets the score variable of this instance.
   * 
   * @param newScore an int to assign
   */
  public void setScore(int newScore) {
    score = newScore;
  }


  /**
   * Adds a set amount to the score variable of this instance.
   * 
   * @param points int amount added
   */
  public void addScore(int points) {
    score += points;
  }
}