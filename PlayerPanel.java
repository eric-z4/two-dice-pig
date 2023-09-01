package edu.ics111.h10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PlayerPanel, a subclass of JPanel that creates a GUI for the Player class.
 * 
 * @author Eric Zhou
 */
public class PlayerPanel extends JPanel {
  private static final long serialVersionUID = 8797662398402639852L;
  private Player plrObj;
  private JLabel nameTxt;
  private JLabel scoreTxt;

  /**
   * Constructs PlayerPanel with a border layout, colored background, border, 
   * and the specified preferred width and height. It includes two JLabels 
   * that take the name and score from the given Player object.
   * 
   * @param playerObj Player object
   * @param width x size of panel
   * @param height y size of panel
   */
  public PlayerPanel(Player playerObj, int width, int height) {
    plrObj = playerObj;
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width, height));
    setBackground(new Color(145, 219, 138));
    setBorder(BorderFactory.createLineBorder(new Color(106, 173, 95), 3));

    nameTxt = new JLabel(plrObj.getName(), JLabel.CENTER);
    scoreTxt = new JLabel(plrObj.getScoreString(), JLabel.CENTER);
    nameTxt.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
    add(nameTxt, BorderLayout.NORTH);
    add(scoreTxt, BorderLayout.CENTER);
  }


  /**
   * Returns the Player instance assigned in this panel.
   * 
   * @return Player object
   */
  public Player getPlrInst() {
    return plrObj;
  }


  /**
   * Changes the color of the border either red or default color to signify who's turn is it.
   * 
   * @param status true/false if it is this panel's turn
   */
  public void turnToggle(boolean status) {
    if (status) {
      setBorder(BorderFactory.createLineBorder(new Color(255, 17, 0), 3));
    } else {
      setBorder(BorderFactory.createLineBorder(new Color(106, 173, 95), 3));
    }
  }


  /**
   * Gets the score from the given Player object and updates the JLabel for score.
   */
  public void updateScore() {
    scoreTxt.setText(plrObj.getScoreString());
  }
}
