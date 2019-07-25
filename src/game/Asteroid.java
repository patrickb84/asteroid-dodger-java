/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Creates an asteroid that moves horizontally on a fixed
 * Y-Coordinate. Asteroids are created on a random x coordinate from 
 * 800 to 1 Million.
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class Asteroid implements Movable{
	
	private Random rand;
	private int x_Coordinate;
	private int y_Coordinate; 
	private boolean isDestroyed;
	private Image sprite;
	private static int movement;
	private List<Image> asteroidImages;
	private static int endOfAsteroids;
	
	/**
	 * Asteroid is created at a random location
	 * and moves left slightly faster than
	 * the background.
	 * 
	 */
	public Asteroid() {
		rand = new Random();
		endOfAsteroids = 1000000;
		x_Coordinate = rand.nextInt(endOfAsteroids) + 800;
		y_Coordinate = rand.nextInt(Game.getGameHeight() - 100);
		asteroidImages = new ArrayList<>();
		getAsteroidImages();
		
		sprite = asteroidImages.get(rand.nextInt(asteroidImages.size()));
		
		//Asteroid should move slightly faster than the background.
		movement = BackgroundImage.getMovement() - 3;
		
	}

	/**
	 * initializes the different asteroid images.
	 */
	private void getAsteroidImages() {
		asteroidImages.add( new ImageIcon(Spaceship.class.getResource(
				"/game/Resources/asteroid7.png")).getImage());
		asteroidImages.add( new ImageIcon(Spaceship.class.getResource(
				"/game/Resources/asteroid8.png")).getImage());
		asteroidImages.add( new ImageIcon(Spaceship.class.getResource(
				"/game/Resources/asteroid9.png")).getImage());	
	}
	
	/**
	 * returns the farthest x value any asteroid could have.
	 * 
	 * @return
	 */
	public static int getEndOfAsteroids() {
		return endOfAsteroids;
	}

	/**
	 * Returns the X-Coordinate of the Asteroid image
	 * 
	 * @return
	 */
	public int getX_Coordinate() {
		return x_Coordinate;
	}

	/**
	 * Returns the Y-Coordinate of the Asteroid image
	 * 
	 * @return
	 */
	public int getY_Coordinate() {
		return y_Coordinate;
	}

	/**
	 * Returns whether or not the ship is destroyed.
	 * 
	 * @return
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * Returns the Asteroid image.
	 * 
	 * @return
	 */
	public Image getSprite() {
		return sprite;
	}

	/**
	 * Returns the speed of the asteroid. 
	 * Movement is negative due to asteroid 
	 * moving left on screen.
	 * 
	 * @return
	 */
	public static int getMovement() {
		return movement;
	}
	
	/**
	 * Returns a rectangle that covers the shape of the
	 * asteroid image. Used for collision detection.
	 * 
	 * @return
	 */
	public Rectangle getBoundsAsRectangle() {
		return new Rectangle(x_Coordinate + 8, y_Coordinate + 8, 70, 60);
	}

	/**
	 * Decrements the X-Coordinate by the value of movement.
	 * Moves the asteroid left across the screen.
	 */
	@Override
	public void move() {
		x_Coordinate += movement;
		
	}
}
