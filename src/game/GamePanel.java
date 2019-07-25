/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5452925014639836146L;
	
	private Spaceship ship;
	private boolean shipIsAWing;
	
	private BackgroundImage background;
	private BackgroundImage background2;
	
	private int backgroundResetValue;
	private RestartWindow restartWindow;
	
	private int numberOfAsteroids = 7500;
	private List<Asteroid> asteroids; 
	private List<Movable> movables;
	private PlayerScore score;

	private String playerName;
	
	// Timer sends an ActionEvent to the actionPerformed
	// method of GamePanel every 5 ms.
	private Timer time = new Timer(5, this);;
	
	/**
	 * 	Creates the GamePanel by initializing a ship, a background,
	 *  a PlayerCcore, and List of asteroids and adds a KeyListener for 
	 *  key control of the spaceship
	 * 
	 * @param shipIsAWing
	 * @param name
	 */
	public GamePanel(boolean shipIsAWing, String name) {
		ship = new Spaceship(shipIsAWing);
		this.shipIsAWing = shipIsAWing;
		playerName = name;
		
		String shipType;
		if(shipIsAWing)
			shipType = "A-Wing";
		else
			shipType = "Viper";
		
		score = new PlayerScore(playerName, shipType, 0);		
		
		asteroids = new ArrayList<>();
		for(int i = 0; i <= numberOfAsteroids; i++) {
			asteroids.add(new Asteroid());
		}
		
		background = new BackgroundImage();
		background2 = new BackgroundImage();
		background2.setX_Coordinate(background.getImageLength());
		backgroundResetValue = -((background.getImageLength() * 2)
				- Game.getGameWidth() - 2);
		
		// everything that moves on the screen
		movables = new ArrayList<>();
		addMovables();
		
		addKeyListener(new ActionListener());
		setFocusable(true);
		setVisible(true);
	}
	
	/**
	 * Adds all of the moving components to a list.
	 */
	private void addMovables() {
		movables.add(background);
		movables.add(background2);
		movables.add(ship);
		movables.addAll(asteroids);
	}
	
	/**
	 * Begins the game swing timer.
	 */
	public void startTimer() {
		time.start();
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	/**
	 * Updates the graphics of the game screen based
	 * on the position and images of the game's objects.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		//restart backgrounds
		restartBackgrounds();

		//Draw backgrounds
		g2D.drawImage(background.getImage(), 
				background.getX_Coordinate(),
				background.getY_COORDINATE(), null);
		g2D.drawImage(background2.getImage(), 
				background2.getX_Coordinate(), 
				background2.getY_COORDINATE(), null);
		
		//Draw ship
		g2D.drawImage(ship.getSprite(), 
				ship.getX_Coordinate(), 
				ship.getY_Coordinate(), null);
		
		//Draw asteroids
		for(Asteroid a : asteroids) {
			g2D.drawImage(a.getSprite(), 
					a.getX_Coordinate(), 
					a.getY_Coordinate(), null);
		}
		
		//Draw the current score.
		g2D.setColor(Color.YELLOW);
		g2D.setFont(Game.getGameFont());
		g2D.drawString(String.format("%S%d", "SCORE ",
				ship.getDistance()), 10, 35);

		//Draw explosion if ship is destroyed
		if(ship.isDestroyed())
			g2D.drawImage(ship.getExplosion(), 
					ship.getX_Coordinate() - 200, 
					ship.getY_Coordinate() - 200, null);
	}

	/**
	 * Moves the background images position to the right of the screen
	 * so the background will continue forever.
	 */
	private void restartBackgrounds() {
		if(checkForEndOfBackground(background.getX_Coordinate()))
			background.setX_Coordinate(Game.getGameWidth());
		
		if(checkForEndOfBackground(background2.getX_Coordinate()))
			background2.setX_Coordinate(Game.getGameWidth());
	}
	
	/**
	 * Used when the game is over to stop the timer,
	 * update the high scores, and open
	 * the restart window.
	 */
	private void endGame() {
		//Stop sending ActionEvents
		if(time.isRunning())
			time.stop();
		
		//Save current score
		score.updateScore();
		
		//read & write high scores
		Game.getMenuHighScores().readHighScores();
		Game.getMenuHighScores().writeHighScores(score);
		
		//Display restart window.
		restartWindow = new RestartWindow(ship.getDistance());
	}
	
	/**
	 * Returns the restart window.
	 * 
	 * @return
	 */
	public RestartWindow getRestartWindow() {
		return restartWindow;
	}

	/**
	 * Checks whether or not the ship has collided with any of the
	 * asteroids.
	 */
	private void checkForCollisions() {
		Rectangle shipBounds = ship.getBoundsAsRectangle();
		
		for(Asteroid a : asteroids) {
			if(shipBounds.intersects(a.getBoundsAsRectangle())) {
				ship.destroyed(true);
			}
		}
	}
	
	/**
	 * checks to see if the background need to be restarted.
	 * 
	 * @param x_Coordinate
	 * @return
	 */
	private boolean checkForEndOfBackground(int x_Coordinate) {
		if(x_Coordinate <= backgroundResetValue) 
			return true;
		return false;
	
	}

	/**
	 * Responsible for updating objects position values and 
	 * repainting the objects to the game screen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!ship.isDestroyed()) {
			for(Movable m : movables) {
				m.move();
			}

			repaint();
			checkForCollisions();
			
			// if the ship is destroyed or the ship reaches 
			// the end of the asteroids then end the game.
			if(ship.isDestroyed() || 
					ship.getDistance() == 
					Asteroid.getEndOfAsteroids()) {
				endGame();
			}
		}
	}
	
	/**
	 * Provides functionality to the ship with the use
	 * of the directional buttons.
	 * 
	 * @author Jordan Davis & Patrick Bradshaw
	 *
	 */
	private class ActionListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			ship.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			ship.keyReleased(e);
		}
	}
	
	/**
	 * Returns the ship object.
	 * 
	 * @return
	 */
	public Spaceship getShip() {
		return ship;
	}
	
	/**
	 * Returns the players name.
	 * 
	 * @return
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Returns whether or not the ship is an A-Wing.
	 * 
	 * @return
	 */
	public boolean isShipAnAWing() {
		return shipIsAWing;
	}
}
