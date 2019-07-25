/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JButton;

/**
 * This is panel is the main menu panel that allows the user
 * to select a new game, see the high scores, view the credits
 * for the game and exit the program.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class MenuTitle extends JPanel{
	
	private static final long serialVersionUID = -848281435820958501L;
	
	/**
	 * Creates the title menu and adds buttons that allow
	 * navigation between the other panels. 
	 */
	public MenuTitle() {
		setBounds(new Rectangle(0, 0, Game.getGameWidth(), Game.getGameHeight()));
		setLayout(null);
		
		JLabel titleImage = createTitleImage();
		add(titleImage);
		
		JButton btnNewGame = createBtnNewGame();
		add(btnNewGame);
		
		JButton btnHighScores = createBtnHighScores();
		add(btnHighScores);
		
		JButton btnCredits = createBtnCredits();
		add(btnCredits);
		
		JButton btnQuit = createBtnQuit();
		add(btnQuit);
	}

	/**
	 * Creates the button that exits the application.
	 * 
	 * @return
	 */
	private JButton createBtnQuit() {
		JButton btnQuit = new JButton("");
		btnQuit.setBounds(338, 461, 140, 40);
		btnQuit.setOpaque(false);
		btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		return btnQuit;
	}

	/**
	 * Creates the button that navigates to the credits
	 * panel.
	 * 
	 * @return
	 */
	private JButton createBtnCredits() {
		JButton btnCredits = new JButton("");
		btnCredits.setBounds(277, 396, 264, 40);
		btnCredits.setOpaque(false);
		btnCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuCredits());
				
			}
			
		});
		return btnCredits;
	}

	/**
	 * Creates the button that navigates to the high
	 * score panel.
	 * 
	 * @return
	 */
	private JButton createBtnHighScores() {
		JButton btnHighScores = new JButton("");
		btnHighScores.setBounds(208, 339, 399, 35);
		btnHighScores.setOpaque(false);
		btnHighScores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuHighScores());
			}
			
		});
		return btnHighScores;
	}

	/**
	 * Creates the button that navigates to the new
	 * game panel.
	 * 
	 * @return
	 */
	private JButton createBtnNewGame() {
		JButton btnNewGame = new JButton("");
		btnNewGame.setBounds(260, 277, 294, 40);
		btnNewGame.setOpaque(false);
		btnNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuNewGame());
				
			}
			
		});
		return btnNewGame;
	}

	/**
	 * Creates the image that displays the words that
	 * the buttons surround.
	 * 
	 * @return
	 */
	private JLabel createTitleImage() {
		JLabel titleImage = new JLabel("");
		titleImage.setBounds(0, 0, Game.getGameWidth(), 600);
		titleImage.setAlignmentY(Component.TOP_ALIGNMENT);
		titleImage.setBorder(null);
		titleImage.setOpaque(true);
		titleImage.setIcon(new ImageIcon(MenuTitle.class.getResource(
				"/game/Resources/title800x600.jpg")));
		return titleImage;
	}
}
