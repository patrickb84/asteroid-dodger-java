/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * Class SpaceShip is an object that represents a spaceship,
 * either a "Viper" from the TV Show "Battlestar Galactica"
 * or an "A-Wing" from the "Star Wars" movies. This class also
 * contains the necessary methods for moving the spaceship
 * in the GamePanel using keyboard controls and the boundaries
 * for that movement.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class Spaceship implements Movable {
	private int x_Coordinate; 
	private int y_Coordinate;
	private boolean isDestroyed;
	private Image sprite;
	private Image explosion;
	private int distance; // How far the ship has travelled
	private int verticalMovement;
	private int horizontalMovement;
	private boolean isAWing; // Determines which ship is being played
	private int shipSpeed = 10;
	
	private static final int TOP_BOUNDARY = 5;
	private static final int BOTTOM_BOUNDARY = Game.getGameHeight() - 65;
	private static final int RIGHT_BOUNDARY = 690;
	private static final int LEFT_BOUNDARY = 10;
	
	/**
	 * The Spaceship constructor with it's initial values.
	 * 
	 * @param isAWing
	 */
	public Spaceship(boolean isAWing) {
		distance = 0;
		
		ImageIcon imgShipViper = new ImageIcon(Spaceship.class.getResource(
				"/game/Resources/spaceship-100px.png"));
		sprite = imgShipViper.getImage();
		
		// This image is shown when the ship hits an asteroid and is destroyed
		ImageIcon imgExplosion = new ImageIcon(Spaceship.class.getResource(
				"/game/Resources/explosion3.png"));
		explosion = imgExplosion.getImage();
		isDestroyed = false;
		
		verticalMovement = 0;
		horizontalMovement = 0;
		x_Coordinate = 100;
		y_Coordinate = 100; 			
		
		this.isAWing = isAWing;
		if(this.isAWing) {
			ImageIcon imgShipAWing = new ImageIcon(Spaceship.class.getResource(
					"/game/Resources/awing-100px.png"));
			sprite = imgShipAWing.getImage();
			
			shipSpeed = 8;
		}
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getX_Coordinate() {
		return x_Coordinate;
	}
	
	public void setX_Coordinate(int x_Coordinate) {
		this.x_Coordinate = x_Coordinate;
	}

	public int getY_Coordinate() {
		return y_Coordinate;
	}
	
	public void setY_Coordinate(int y_Coordinate) {
		this.y_Coordinate = y_Coordinate;
	}

	/**
	 * Getter for whether or not the ship is destroyed and the game
	 * is over.
	 * 
	 * @return
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
	/**
	 * Sets to true the moment the ship is destroyed.
	 * 
	 * @param b
	 */
	public void destroyed(boolean b) {
		isDestroyed = b;
	}

	public Image getSprite() {
		return sprite;
	}
	
	public void setHorizontalMovement(int horizontalMovment) {
		this.horizontalMovement = horizontalMovment;
	}
	
	public void setVerticalMovement(int verticalMovement) {
		this.verticalMovement = verticalMovement;
	}
	
	public Image getExplosion() {
		return explosion;
	}
	
	/**
	 * Used for collision detection, centers a rectangle over a sprite.
	 * 
	 * @return
	 */
	public Rectangle getBoundsAsRectangle() {
		return new Rectangle(x_Coordinate, y_Coordinate + 5, 90, 26);
	}
	
	/**
	 * This method is what moves the ship up and down on the screen.
	 */
	@Override
	public void move() {
		y_Coordinate += verticalMovement;
		x_Coordinate += horizontalMovement;
		distance +=  -1 * BackgroundImage.getMovement();
		
		// Keeps ship inside pane
		if(y_Coordinate <= TOP_BOUNDARY)
			y_Coordinate = TOP_BOUNDARY;
		if(y_Coordinate >= BOTTOM_BOUNDARY) 
			y_Coordinate = BOTTOM_BOUNDARY;
		
		if(x_Coordinate <= LEFT_BOUNDARY)
			x_Coordinate = LEFT_BOUNDARY;
		if(x_Coordinate >= RIGHT_BOUNDARY)
			x_Coordinate = RIGHT_BOUNDARY;
	}
	
	/**
	 * Configures the game play keys for moving the ship.
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			verticalMovement = -shipSpeed;
		}
		if (key == KeyEvent.VK_DOWN) {
			verticalMovement = shipSpeed;
		}
		if (key == KeyEvent.VK_RIGHT) {
			horizontalMovement = shipSpeed;
			distance += shipSpeed;
		}
		if (key == KeyEvent.VK_LEFT) {
			horizontalMovement = -shipSpeed;
			distance += -shipSpeed;
		}
	}
	
	/**
	 * When game play keys are released, this method returns movement
	 * back to 0.
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			verticalMovement = 0;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			horizontalMovement = 0;
		}
	}
}
