/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Creates a background image that displays on
 * the GamePanel screen. 
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class BackgroundImage implements Movable{
	private int x_Coordinate;
	private final int Y_COORDINATE = 0;
	private Image image;
	private static int movement; //speed of the background
	private int imageLength;

	/**
	 * Creates the background image and sets the movement
	 * of the background. 
	 */
	public BackgroundImage() {
		x_Coordinate = 0;
		image = (new ImageIcon(BackgroundImage.class.getResource(
				"/game/Resources/background1-2880x600px.png"))).getImage();
		movement = -1;
		imageLength = image.getWidth(null);
	}
	
	/**
	 * Sets the X-Coordinate of the background.
	 * Used to reset the X position on the background
	 * while side scrolling.
	 * 
	 * @param x_Coordinate
	 */
	public void setX_Coordinate(int x_Coordinate) {
		this.x_Coordinate = x_Coordinate;
	}
	
	/**
	 * Returns the length of the 
	 * asteroid image.
	 * 
	 * @return
	 */
	public int getImageLength() {
		return imageLength;
	}

	/**
	 * Returns the X-Coordinate of the 
	 * asteroid
	 * 
	 * @return
	 */
	public int getX_Coordinate() {
		return x_Coordinate;
	}
	
	/**
	 * Returns the Y-Coordinate of the 
	 * asteroid
	 * 
	 * @return
	 */
	public int getY_COORDINATE() {
		return Y_COORDINATE;
	}
	
	/**
	 * Returns the Asteroid Image.
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Returns the movement value of the
	 * asteroid.
	 * 
	 * @return
	 */
	public static int getMovement() {
		return movement;
	}
	
	/**
	 * Decrements the X-coordinate by the 
	 * value of the movement.
	 */
	@Override
	public void move() {
		x_Coordinate += movement; 
	}	
}
