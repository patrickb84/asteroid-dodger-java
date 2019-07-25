/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * The panel before the start of game play where the player
 * enters his or her name and selects a spaceship. This
 * panel also has buttons for navigating back to the Main
 * Menu as well as quitting the game.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class MenuNewGame extends JPanel{
	private static final long serialVersionUID = -848281435820958501L;
	private JTextField nameField;
	
	/**
	 * Creates the menu for the new game. Adds a background image
	 * for the panel and buttons for navigation. Also creates the
	 * text field for the player to input their name.
	 */
	public MenuNewGame() {
		setBounds(0, 0, Game.getGameWidth(), Game.getGameHeight());
		setLayout(null);
		
		nameField = new JTextField();
		createNameField();
		add(nameField);
		
		JLabel newGameImage = new JLabel("");
		getNewGameImage(newGameImage);
		add(newGameImage);
		
		JButton btnShipViper = new JButton("");
		createBtnShipViper(btnShipViper);
		add(btnShipViper);
		
		JButton btnShipAWing = new JButton("");
		createBtnShipAWing(btnShipAWing);
		add(btnShipAWing);
		
		JButton btnNavMainMenu = new JButton("");
		createBtnNavMainMenu(btnNavMainMenu);
		add(btnNavMainMenu);
		
		JButton btnNavQuit = new JButton("");
		createBtnNavQuit(btnNavQuit);
		add(btnNavQuit);
	}

	/**
	 * This method is responsible for displaying the main image
	 * of the panel.
	 * 
	 * @param newGameImage
	 */
	private void getNewGameImage(JLabel newGameImage) {
		newGameImage.setBounds(0, 0, Game.getGameWidth(), 600);
		newGameImage.setAlignmentY(Component.TOP_ALIGNMENT);
		newGameImage.setBorder(null);
		newGameImage.setOpaque(true);
		newGameImage.setIcon(new ImageIcon(MenuNewGame.class.getResource(
				"/game/Resources/newgame800x600.jpg")));
	}

	/**
	 * This method creates the field where the player enters their name.
	 */
	private void createNameField() {
		nameField.setOpaque(true);
		nameField.setBounds(410, 210, 276, 26);
		nameField.setMinimumSize(new Dimension(276, 26));
		nameField.setColumns(7);
	}
	
	/**
	 * Retrieves whatever information is entered into the Name Field.
	 * @return
	 */
	public JTextField getNameField() {
		return nameField;
	}
	
	/**
	 * Creates the button so the player can select the Viper spaceship.
	 * 
	 * @param btnShipViper
	 */
	private void createBtnShipViper(JButton btnShipViper) {
		btnShipViper.setOpaque(false);
		btnShipViper.setBounds(82, 340, 301, 212);
		
		// this ActionListener collects the player name and the ship chosen
		btnShipViper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().length() > 0) {
					boolean isAWing = false;
					Game.resetGamePanel(isAWing, nameField.getText());
					Game.setPanel(Game.getGamePanel());
				} 
			}
		});
	}
	
	/**
	 * Creates the button so that the player can select the A-Wing spaceship.
	 * 
	 * @param btnShipAWing
	 */
	private void createBtnShipAWing(JButton btnShipAWing) {
		btnShipAWing.setOpaque(false);
		btnShipAWing.setBounds(467, 340, 242, 212);
		
		// this ActionListener collects the player name and the ship chosen
		btnShipAWing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().length() > 0) {
					boolean isAWing = true;
					Game.resetGamePanel(isAWing, nameField.getText());
					Game.setPanel(Game.getGamePanel());
				}
			}
		});
	}
	
	/**
	 * Creates the button for navigating back to the Main Menu.
	 * @param btnNavMainMenu
	 */
	private void createBtnNavMainMenu(JButton btnNavMainMenu) {
		btnNavMainMenu.setOpaque(false);
		btnNavMainMenu.setBounds(0, 574, 128, 26);
		btnNavMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuTitle());
			}
		});
	}
	
	/**
	 * The Quit button.
	 * 
	 * @param btnNavQuit
	 */
	private void createBtnNavQuit(JButton btnNavQuit) {
		btnNavQuit.setOpaque(false);
		btnNavQuit.setBounds(732, 571, 68, 29);
		btnNavQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}	
}
