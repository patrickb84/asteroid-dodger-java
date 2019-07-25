/*
 * Authors: Jordan Davis & Patrick Bradshaw
 * CSIS-1410 
 * A09 Team Assignment
 * Last Edited: 12/2/2017
 */

package game;

/**
 * Class PlayerScore contains the methods that collect
 * all the pertinent information for storing player
 * scores as well as for sorting them. 
 * 
 * @author Jordan Davis & Patrick Bradshaw
 *
 */
public class PlayerScore implements Comparable<PlayerScore> {
	private String name;
	private String shipType;
	private int score;
	
	/**
	 * The constructor initializes with a name that shortens any name
	 * that is longer than 8 characters, a ship type, and the score.
	 * 
	 * @param name
	 * @param shipType
	 * @param score
	 */
	public PlayerScore(String name, String shipType, int score){
		this.name = limitNameLength(name);
		this.shipType = shipType;
		this.score = score;
	}
	
	/**
	 * This method formats a name entered by the player if it is too
	 * long to fit in the High Scores menu.
	 * 
	 * @param name
	 * @return
	 */
	private String limitNameLength(String name) {
		if(name.length() > 7) {
			name = name.substring(0, 7);
			name = name + "...";
		}
		return name;
	}

	/**
	 * Returns the players name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the type of ship.
	 * 
	 * @return
	 */
	public String getShipType() {
		return shipType;
	}

	/**
	 * Returns the score.
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Updates the player score with the latest distance of
	 * the ship. 
	 */
	public void updateScore() {
		score = Game.getGamePanel().getShip().getDistance() * 4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result + ((shipType == null) ? 0 : 
			shipType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerScore other = (PlayerScore) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		if (shipType == null) {
			if (other.shipType != null)
				return false;
		} else if (!shipType.equals(other.shipType))
			return false;
		return true;
	}
	
	/**
	 * Returns a string representation of the player score
	 * formatted to fit in the high scores menu.
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%-12S %-7S %9d", name, shipType, score);
	}

	/**
	 * Compares two player scores and returns a positive value if this
	 * player score is higher than the other score, 0 if they are the same,
	 * and a negative number if the other score is higher than this score.
	 * This methods allows for the sorting of scores.
	 */
	public int compareTo(PlayerScore otherScore) {
		return otherScore.getScore() - this.score;
	}	
}
