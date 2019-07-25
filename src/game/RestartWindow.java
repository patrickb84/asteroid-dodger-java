/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class RestartWindow extends JFrame {
	private static final long serialVersionUID = 7002361522558998941L;
	private static final Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);

	public RestartWindow(int score) {
		getContentPane().setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400, 325);
		setVisible(true);
		setLocationRelativeTo(Game.getFrame());
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		//FIX JLABEL TO SHOW THE SCORE
		JLabel lblScore = new JLabel("");
		createLblScore(lblScore);
		getContentPane().add(lblScore);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RestartWindow.class.getResource(
				"/game/Resources/restartwindow400x300.png")));
		label.setBounds(0, 0, 400, 300);
		getContentPane().add(label);
		
		JButton btnHighScores = new JButton("");
		createBtnHighScores(btnHighScores);
		getContentPane().add(btnHighScores);
		
		JButton btnRestart = new JButton("");
		createBtnRestart(btnRestart);
		getContentPane().add(btnRestart);
		
		JButton btnMainMenu = new JButton("");
		createBtnMainMenu(btnMainMenu);
		getContentPane().add(btnMainMenu);
		
		JButton btnQuit = new JButton("");
		createBtnQuit(btnQuit);
		getContentPane().add(btnQuit);
		
	}


	private void createLblScore(JLabel lblScore) {
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(163, 99, 201, 29);
		lblScore.setOpaque(false);
		lblScore.setBackground(TRANSPARENT);
		lblScore.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblScore.setFont(new Font("Dialog", Font.BOLD, 31));
		lblScore.setOpaque(true);
		lblScore.setForeground(new Color(1f, 1f, 0f, .8f));
		lblScore.setText("" + Game.getGamePanel().getShip().getDistance());
	}
	

	private void createBtnQuit(JButton btnQuit) {
		btnQuit.setBounds(149, 257, 93, 29);
		btnQuit.setOpaque(false);
		btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
	}

	private void createBtnMainMenu(JButton btnMainMenu) {
		btnMainMenu.setBounds(97, 222, 194, 29);
		btnMainMenu.setOpaque(false);
		btnMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setPanel(Game.getMenuTitle());
				
				// Discards the window
				Game.getGamePanel().getRestartWindow().setVisible(false);
				Game.getGamePanel().getRestartWindow().dispose();
			}
			
		});
	}

	private void createBtnRestart(JButton btnRestart) {
		btnRestart.setBounds(103, 181, 187, 29);
		btnRestart.setOpaque(false);
		btnRestart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Discards the window
				Game.getGamePanel().getRestartWindow().setVisible(false);
				Game.getGamePanel().getRestartWindow().dispose();
				
				Game.resetGamePanel(Game.getGamePanel().isShipAnAWing(),
						Game.getGamePanel().getPlayerName());
				Game.setPanel(Game.getGamePanel());
			}
			
		});
	}

	private void createBtnHighScores(JButton btnHighScores) {
		btnHighScores.setBounds(65, 146, 258, 29);
		btnHighScores.setOpaque(false);
		btnHighScores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getMenuHighScores().updateHighScoresText();
				Game.setPanel(Game.getMenuHighScores());
				
				// Discards the window
				Game.getGamePanel().getRestartWindow().setVisible(false);
				Game.getGamePanel().getRestartWindow().dispose();
			}
			
		});
	}
}
