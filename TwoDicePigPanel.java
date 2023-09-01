package edu.ics111.h10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * TwoDicePigPanel, a subclass of JPanel that visually simulates playing two dice pig.
 * 
 * @author Eric Zhou
 */
public class TwoDicePigPanel extends JPanel {
  private static final long serialVersionUID = 4862152906874289652L;
  private static final int playerAmt = 2;
  private static PlayerPanel[] plrPanelArr = new PlayerPanel[playerAmt];
  private static DicePanel dicePanel;
  private static TurnPanel turnPanel;
  private static int turn = 0; // Turn index for plrPanelArr
  private static int turnScore = 0; // The amount of score earned in a player's turn

  /**
   * Required main subroutine.
   */
  public static void main(String[] args) {
    // TODO Create a JFrame window with set size and location
    // Modify DicePanel from H09 to be usable here
    // Utilizing border layout, add the dice panel to the center
    // Using JOptionPane.showInputDialog(), get the player's names
    // Create a PlayerPanel class that contains the player's name and score
    // The PlayerPanel class should have JLabels to show name and score
    // The class should also change border color to represent who's turn
    // Add the player panels to the west and east
    // Add a panel to the south that contains JButtons for hold and roll and...
    // a JLabel to show a player's current turn score and updates every roll
    // Beginning of each turn, player will automatically roll
    // Rules of Two Dice Pig is checked when the dice is rolled and act accordingly
    // Rolled 1, no turn score, change turn
    // Pair of ones, lose score, change turn
    // Same pair, add turn score, auto roll
    // Other cases, add turn score, user decides to roll or hold
    // Clicking the hold button will add the turn score to player's score and end turn
    // Clicking the roll button will roll the dice again and check the rules
    JFrame window = new JFrame("Two Dice Pig Game");
    TwoDicePigPanel content = new TwoDicePigPanel();

    window.setLocation(200, 200);
    window.setContentPane(content);
    window.setDefaultCloseOperation(3);
    window.pack();
    window.setVisible(true);

    TwoDicePigPanel.playerTurn();
  }


  /**
   * Constructor that sets the panel's layout and background. In addition, it creates 
   * two PlayerPanels, a DicePanel, and a TurnPanel and adds them to the TwoDicePigPanel.
   */
  public TwoDicePigPanel() {
    setLayout(new BorderLayout());
    setBackground(new Color(146, 219, 134));

    for (int i = 0; i < playerAmt; i++) {
      plrPanelArr[i] = setupPlayerPanel(i + 1, 150, 200);
    }
    add(BorderLayout.WEST, plrPanelArr[0]);
    add(BorderLayout.EAST, plrPanelArr[1]);

    dicePanel = new DicePanel(180, 200);
    add(BorderLayout.CENTER, dicePanel);

    turnPanel = new TurnPanel(400, 100);
    add(BorderLayout.SOUTH, turnPanel);
  }


  /**
   * Simulates a player's turn in two dice pig. It will roll a pair of dice and check the 
   * values of both dice. If the values meet certain conditions specified by two dice pig, 
   * players may lose points, lose their turn, or be forced to roll. Otherwise, they can 
   * hold to lock in their earned points, or roll again for a chance to earn more points.
   * 
   * <p>Modified from H07 for use in this class and be graphically represented
   */
  public static void playerTurn() {
    Player player = plrPanelArr[turn].getPlrInst();
    plrPanelArr[turn].turnToggle(true);

    PairOfDice dies = dicePanel.getDiceInst();
    dicePanel.roll();
    turnPanel.addText(player.getName() + " rolled a " + dies.die1 + " and a " + dies.die2 + "\n");

    if (dies.die1 == 1 && dies.die2 == 1) {
      turnScore = -(player.getScore());
      endTurn();
    } else if (dies.die1 == 1 || dies.die2 == 1) {
      turnScore = 0;
      endTurn();
    } else if (dies.die1 == dies.die2) {
      turnScore += dies.total();
      turnPanel.addText("DOUBLE!! Rolling again... \n");
      playerTurn();
    } else {
      turnScore += dies.total();
      turnPanel.addText(player.getName() + " has current turn score: " 
          + turnScore + " [hold/roll]\n");
      turnPanel.btnToggle(true);
    }
  }


  /**
   * Simulates the end of a player's turn. It changes the player's border, adds (or subtracts) 
   * turn score to the player's score, and prepares for the next turn.
   * 
   * <p>If the player would have more than 100 points when this method is called, the program 
   * ends and declares said player the winner. Otherwise, the turn switches and playerTurn is 
   * called until one player reaches 100.
   */
  public static void endTurn() {
    Player player = plrPanelArr[turn].getPlrInst();
    plrPanelArr[turn].turnToggle(false);
    plrPanelArr[turn].getPlrInst().addScore(turnScore);
    plrPanelArr[turn].updateScore();
    turnScore = 0;
    turnPanel.clearText();

    PairOfDice dies = dicePanel.getDiceInst();
    if (dies.die1 == 1 && dies.die2 == 1) {
      turnPanel.addText(player.getName() 
          + " rolled snake eyes in the previous turn and lost all points... \n");
    } else if (dies.die1 == 1 || dies.die2 == 1) {
      turnPanel.addText(player.getName() 
          + " rolled a 1 in the previous turn and skipped their turn... \n");
    }

    if (plrPanelArr[turn].getPlrInst().getScore() >= 100) {
      JOptionPane.showMessageDialog(dicePanel, player.getName() + " WON THE GAME!");
      System.exit(1);
    } else {
      turn = 1 - (turn * 1);
      playerTurn();
    }
  }


  /**
   * A helper method for handling user input for the player's name and constructing the 
   * PlayerPanel with the inputed name. If the input dialog is blank, the PlayerPanel will have 
   * a default name. If the dialog is canceled or exited, the program will exit too.
   * 
   * @param plrIndex an index number for the player
   * @param width x size of PlayerPanel
   * @param height y size of PlayerPanel
   * @return PlayerPanel object with a given or default name
   */
  private static PlayerPanel setupPlayerPanel(int plrIndex, int width, int height) {
    String playerName = JOptionPane.showInputDialog(null,
        "What is player " + plrIndex + "'s name? " 
            + "(exiting or canceling will stop the program)");
    try {
      if (playerName.isBlank()) {
        return new PlayerPanel(new Player(), width, height);
      } else {
        return new PlayerPanel(new Player(playerName), width, height);
      }
    } catch (Exception error) {
      System.exit(1);
      return null;
    }
  }

  /**
   * TurnPanel, a nested class in TwoDicePigPanel that provides the roll and hold buttons and 
   * the text that describes what is happening in a turn.
   * 
   * @author Eric Zhou
   */
  static class TurnPanel extends JPanel {
    private static final long serialVersionUID = -8417970299697610664L;
    private JTextArea turnDesc;
    private JButton holdBtn;
    private JButton rollBtn;

    /**
     * Constructs TurnPanel with border layout, background, and specified width and height. 
     * It includes hold and roll JButtons that call endTurn and playerTurn respectively, 
     * and a scrollable JTextArea that would describe what is happening.
     * 
     * <p>Adding JTextArea was inspired by Yang Qian's example in Section 3 Lab
     * 
     * @param width x size of the panel
     * @param height y size of the panel
     */
    public TurnPanel(int width, int height) {
      setLayout(new BorderLayout());
      setPreferredSize(new Dimension(width, height));
      setBackground(new Color(245, 245, 245));

      holdBtn = new JButton("Hold");
      holdBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          btnToggle(false);
          endTurn();
        }
      });
      add(BorderLayout.WEST, holdBtn);

      rollBtn = new JButton("Roll");
      rollBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          btnToggle(false);
          playerTurn();
        }
      });
      add(BorderLayout.EAST, rollBtn);

      turnDesc = new JTextArea();
      JScrollPane areaWithScrollBar = new JScrollPane(turnDesc);
      areaWithScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      areaWithScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      turnDesc.setEnabled(false);
      turnDesc.setDisabledTextColor(Color.DARK_GRAY);
      add(BorderLayout.CENTER, areaWithScrollBar);
      btnToggle(false);
    }


    /**
     * Enables and disables the hold and roll buttons in order for them to either 
     * clickable or unclickable.
     * 
     * @param isEnabled the setting of buttons
     */
    public void btnToggle(boolean isEnabled) {
      holdBtn.setEnabled(isEnabled);
      rollBtn.setEnabled(isEnabled);
    }


    /**
     * A simple method for adding text to JTextArea in this panel.
     * 
     * @param newTxt String being set
     */
    public void addText(String newTxt) {
      turnDesc.append(newTxt);
    }


    /**
     * A simple method for clearing text in JTextArea.
     */
    public void clearText() {
      turnDesc.setText("");
    }
  }
}
