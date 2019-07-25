/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * The window of the game containing the contents
 * of the game and the main method.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class Game {
	private static final int gameWidth = 800;
	private static final int gameHeight = 625;
	private static Font gameFont = 
			new Font("Franklin Gothic Medium", Font.BOLD, 30);
	private static JFrame frame;
	private static MenuTitle menuTitle = new MenuTitle();
	private static GamePanel gamePanel;
	private static MenuNewGame menuNewGame = new MenuNewGame();
	private static MenuHighScores menuHighScores = new MenuHighScores();
	private static JPanel currentPanel;
	private static MenuCredits menuCredits = new MenuCredits();
	
	/**
	 * Starts the game window.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new JFrame();
		initializeFrame();
	}

	/**
	 * Initializes the window of the game.
	 */
	private static void initializeFrame() {
		currentPanel = menuTitle;
		setPanel(currentPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(0, 0, gameWidth, gameHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
	}
	
	/**
	 * Returns this window frame.
	 * 
	 * @return
	 */
	public static JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Resets the game panel with a new game.
	 * This will reset all of the variables in 
	 * the GamePanel.
	 * 
	 * @param isAWing
	 * @param name
	 */
	public static void resetGamePanel(boolean isAWing, String name) {
		gamePanel = new GamePanel(isAWing, name);
	}
	
	/**
	 * Method to retrieve the high scores menu panel.
	 * 
	 * @return
	 */
	public static MenuHighScores getMenuHighScores() {
		return menuHighScores;
	}

	/**
	 * Method to retrieve the Game panel where
	 * gameplay occurs.
	 * 
	 * @return
	 */
	public static GamePanel getGamePanel() {
		return gamePanel;
	}
	
	/**
	 * Method to retrieve the Credits window.
	 * 
	 * @return
	 */
	public static MenuCredits getMenuCredits() {
		return menuCredits;
	}
	
	/**
	 * Method to retrieve the Title Menu panel.
	 * 
	 * @return
	 */
	public static JPanel getMenuTitle() {
		return menuTitle;
	}
	
	/**
	 * Method to retrieve the New Game Menu panel.
	 * 
	 * @return
	 */
	public static JPanel getMenuNewGame() {
		return menuNewGame;
	}
	
	/**
	 * Method to get the same standard width for each panel.
	 * 
	 * @return
	 */
	public static int getGameWidth() {
		return gameWidth;
	}
	
	/**
	 * Method to get the same standard height for each panel.
	 * 
	 * @return
	 */
	public static int getGameHeight() {
		return gameHeight;
	}
	
	/**
	 * Method to get the standard game font for any panel
	 * that needs it.
	 * 
	 * @return
	 */
	public static Font getGameFont() {
		return gameFont;
	}

	/**
	 * This method changes one panel into another.
	 * 
	 * @param panel
	 */
	public static void setPanel(JPanel panel) {
		if (currentPanel != null)
			frame.remove(currentPanel);
		currentPanel = panel;
		menuTitle.setBackground(Color.BLACK);
		frame.getContentPane().add(currentPanel, BorderLayout.CENTER);
		
		if (currentPanel.equals(gamePanel)) {
			((GamePanel)gamePanel).startTimer();
		}
		
		frame.validate();
		currentPanel.requestFocusInWindow();
		frame.repaint();
	}
}