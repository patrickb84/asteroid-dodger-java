/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This is the panel that displays the high scores that
 * have been saved in a high score file. It also is responsible
 * for the reading and writing of that file.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class MenuHighScores extends JPanel{
	
	private static final long serialVersionUID = -7254697097284508844L;
	private JTextArea textAreaScores;
	private List<PlayerScore> highScores = new ArrayList<>();
	private static final Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);

	/**
	 * Creates the high score menu panel and adds buttons for 
	 * navigation as well as a text field that display the high scores.
	 */
	public MenuHighScores() {
		setBounds(new Rectangle(0, 0, Game.getGameWidth(), 600));
		setLayout(null);
		
		textAreaScores = new JTextArea();
		createTextAreaScores();
		add(textAreaScores);
		
		JLabel lblImage = new JLabel("");
		createLblImage(lblImage);
		add(lblImage);
		
		JButton btnMainMenu = new JButton("");
		createBtnMainMenu(btnMainMenu);
		add(btnMainMenu);
		
		JButton btnQuit = new JButton("");
		createBtnQuit(btnQuit);
		add(btnQuit);
		
		updateHighScoresText();
	}

	/**
	 * Creates the button that allows the user to quit the
	 * application.
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
	 * Creates the button that navigates back to the main menu.
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
	
	/**
	 * Creates the background image for the menu where the text area 
	 * and button sit on top of.
	 * 
	 * @param lblImage
	 */
	private void createLblImage(JLabel lblImage) {
		lblImage.setIcon(new ImageIcon(MenuHighScores.class.getResource(
				"/game/Resources/scores800x600.png")));
		lblImage.setBounds(0, 0, Game.getGameWidth(), 600);
	}

	/**
	 * creates the text area where the high scores are displayed.
	 */
	private void createTextAreaScores() {
		textAreaScores.setBounds(196, 264, 434, 263);
		textAreaScores.setOpaque(true);
		textAreaScores.setEditable(false);
		textAreaScores.setForeground(Color.WHITE);
		textAreaScores.setBackground(TRANSPARENT);
		textAreaScores.setFont(new Font(
				"Lucida Sans Typewriter", Font.BOLD, 20));
	}
	
	/**
	 * Updates the high scores text from the high scores
	 * file. 
	 */
	public void updateHighScoresText(){
		StringBuilder sb = new StringBuilder();
		readHighScores();

		for(int i = 0; i < highScores.size(); i++) {
			
			sb.append(String.format("%2d. ", i + 1));

			sb.append(highScores.get(i).toString());
			sb.append("\n");
		}
		
		textAreaScores.setText(sb.toString());
	}
	
	/**
	 * Creates player score objects out of a line of 
	 * text read in from the high scores file.
	 * 
	 * @param nextLine
	 * @return
	 */
	private PlayerScore createPlayerScore(String nextLine) {
		String readName = "";
		String readShipType = "";
		int readScore= 0;
		
		try {
			String[] line = nextLine.split(",");

			readName = line[0];
			readShipType = line[1];
			readScore = Integer.parseInt(line[2]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return new PlayerScore(readName, readShipType, readScore);
	}
	
	/**
	 * Reads the file high scores and adds the player score objects to
	 * the high scores list.
	 */
	public void readHighScores() {
		highScores.clear();
		try (BufferedReader reader = 
				new BufferedReader(new FileReader(
						"src/game/Resources/highScores.csv"))) {

			PlayerScore readScore;
			
			while (reader.ready()) {
				String line = reader.readLine();
				
				readScore = createPlayerScore(line);
				
				if(readScore != null) {
					highScores.add(readScore);
				}
			}
		} catch (IOException e ) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Writes the high scores to a file. This method adds the score of
	 * the current player to the high scores list, sorts the list high
	 * to low, then only writes the top ten scores to the file. If the 
	 * high scores list is less than ten, then it only writes until the
	 * end of the high scores list.
	 * 
	 * @param currentScore
	 */
	public void writeHighScores(PlayerScore currentScore) {
		highScores.clear();
		readHighScores();
		highScores.add(currentScore);
		highScores.sort(null);
			
		try(PrintWriter writer = new PrintWriter(
				"src/game/Resources/highScores.csv")) {
			
			if(highScores.size() >= 10) {
				for(int i = 0; i < 10; i++) {
					writer.println(String.format("%S,%S,%d",
							highScores.get(i).getName(),
							highScores.get(i).getShipType(), 
							highScores.get(i).getScore()));
				}
			} else {
				for(int i = 0; i < highScores.size(); i++) {
					writer.println(String.format("%S,%S,%d",
							highScores.get(i).getName(),
							highScores.get(i).getShipType(), 
							highScores.get(i).getScore()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
