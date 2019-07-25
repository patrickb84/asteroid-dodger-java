/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The panel that displays the credits and attributions
 * for the elements of the game. This panel also has
 * buttons for navigating back to the Main Menu as well
 * as quitting the game.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class MenuCredits extends JPanel {
	private static final long serialVersionUID = -589402082361605953L;

	/**
	 * Creates the Menu and adds buttons for navigation.
	 */
	public MenuCredits() {
		setBounds(new Rectangle(0, 0, Game.getGameWidth(), 600));
		setLayout(null);
		
		JLabel lblMenuCreditsBackground = createMenuCreditsBackground();
		add(lblMenuCreditsBackground);
		
		JButton btnMainMenu = new JButton("");
		createBtnMainMenu(btnMainMenu);
		add(btnMainMenu);
		
		JButton btnQuit = new JButton("");
		createBtnQuit(btnQuit);
		add(btnQuit);
	}

	/**
	 * This method shows the main image that contains all of the 
	 * credits.
	 * 
	 * @return
	 */
	private JLabel createMenuCreditsBackground() {
		JLabel lblMenuCreditsBackground = new JLabel("");
		lblMenuCreditsBackground.setIcon(new ImageIcon(
				MenuCredits.class.getResource(
						"/game/Resources/credits800x600.png")));
		lblMenuCreditsBackground.setBounds(0, 0, 800, 600);
		return lblMenuCreditsBackground;
	}
	
	/**
	 * The Quit button.
	 * 
	 * @param btnQuit
	 */
	private void createBtnQuit(JButton btnQuit) {
		btnQuit.setBounds(737, 574, 63, 26);
		btnQuit.setOpaque(false);
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * The button that takes you to the Main Menu.
	 * 
	 * @param btnMainMenu
	 */
	private void createBtnMainMenu(JButton btnMainMenu) {
		btnMainMenu.setBounds(0, 577, 128, 23);
		btnMainMenu.setOpaque(false);
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuTitle());
			}
		});
	}
}
