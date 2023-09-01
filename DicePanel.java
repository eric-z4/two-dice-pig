package edu.ics111.h10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * DicePanel, a subclass of JPanel that creates a GUI for the PairOfDice class.
 * 
 * @author Eric Zhou
 */
public class DicePanel extends JPanel {
  private static final long serialVersionUID = -6268574398660281108L;
  private PairOfDice diesObj;

  /**
   * Constructs a PairOfDice object and panel with a background, border, and specified size.
   * 
   * @param width x size of panel
   * @param height y size of panel
   */
  public DicePanel(int width, int height) {
    diesObj = new PairOfDice();
    setPreferredSize(new Dimension(width, height));
    setBackground(new Color(146, 219, 134));
    setBorder(BorderFactory.createLineBorder(new Color(106, 173, 95), 3));
  }


  /**
   * Draws the given die at x and y.
   * 
   * <p>Initially copied and modified from H09 module and Exercise 6.3 solution in textbook.
   * 
   * @param g The Graphics context.
   * @param dieNum the number of the die either 1 or 2.
   * @param x the x location for the die.
   * @param y the y location for the die.
   */
  private void drawDie(Graphics g, int dieNum, int x, int y) {
    int pipSize = 11;

    g.setColor(Color.white);
    g.fillRect(x, y, 45, 45);
    g.setColor(Color.black);
    g.drawRect(x, y, 44, 44);
    if (dieNum % 2 == 1) {
      g.fillOval(x + 16, y + 16, pipSize, pipSize);
    }
    if (dieNum >= 2) {
      g.fillOval(x + 5, y + 5, pipSize, pipSize);
      g.fillOval(x + 28, y + 28, pipSize, pipSize);
    }
    if (dieNum >= 4) {
      g.fillOval(x + 5, y + 28, pipSize, pipSize);
      g.fillOval(x + 28, y + 5, pipSize, pipSize);
    }
    if (dieNum == 6) {
      g.fillOval(x + 5, y + 16, pipSize, pipSize);
      g.fillOval(x + 28, y + 16, pipSize, pipSize);
    }
  }


  /**
   * Rolls the pair of dice in the PairOfDice object and calls repaint to update the GUI graphics.
   */
  public void roll() {
    diesObj.roll();
    repaint();
  }


  /**
   * Returns the PairOfDice object assigned in this panel.
   * 
   * @return PairOfDice object
   */
  public PairOfDice getDiceInst() {
    return diesObj;
  }


  /**
   * Draws two dies with antialising to make it look nicer.
   * 
   * <p>Initially copied and modified from H09 module and Exercise 6.3 solution in textbook.
   * 
   * @param g The Graphics context
   */
  public void paintComponent(Graphics g) {
    int width = getSize().width;
    int height = getSize().height;
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawRect(0, 0, width + 1, height + 1);
    drawDie(g, diesObj.die1, (int) (width * 0.2), (int) (height * 0.2));
    drawDie(g, diesObj.die2, (int) (width * 0.6), (int) (height * 0.6));
  }
}
