package zombiegame;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Player extends Creature implements ICreature {
	private PApplet parent;

	public enum Assignment {
		IDLE, MOVE, LOOK
	}

	public Assignment assi;

	static PVector mouseClickPos;

	private int leftHandPosY;
	private int rightHandPosY;

	static int numberOfPlayers = 2;

	private static boolean isNewMousePos;

	// Color
	private Color armsColor;

	public Player(PApplet parent) {
		this.parent = parent;

		parent.ellipseMode(PConstants.CENTER); // Makes the ellipse centered

		// Player stats
		health = 100;

		// Player pos
		location = new PVector(parent.width / 2, parent.height / 2);

		randomLocation = look = location;
		setMouseClickPos(parent.width / 2, parent.height / 2);

		randomLocationGenerated = false;
		rightHandPosY = 0;
		leftHandPosY = 0;
		speed = 1;

		setIsNewMousePos(false);

		// Controls
		assi = Assignment.IDLE;

		// Color
		color = new Color(100, 125, 90); // Color of player
		armsColor = new Color(80, 100, 70); // Color of the arms
	}

	public void line() {
		// Line between player and mouseClickPos
		parent.stroke(1);
		parent.line(location.x, location.y, mouseClickPos.x, mouseClickPos.y);
	}

	@Override
	public void render() {

		// Rotates towards where the player is walking
		float angle = PApplet.atan2(look.y - location.y, look.x - location.x);

		parent.pushMatrix(); // Saves

		parent.translate(location.x, location.y);
		parent.rotate(angle + PConstants.HALF_PI); // Rotates the player

		// Body
		parent.noStroke(); // No black lines around the ellipse
		parent.fill(color.getRGB()); // Sets the color of the player
		parent.ellipse(0, 0, 20, 20); // Creates an ellipse

		// Arms
		parent.stroke(1); // Black lines around the ellipse
		parent.fill(armsColor.getRGB());
		parent.ellipse(0 + 10, 0 + rightHandPosY - 4, 5, 5);
		parent.ellipse(0 - 10, 0 + leftHandPosY - 4, 5, 5);

		// Head
		parent.ellipse(0, 0, 10, 10);

		parent.popMatrix(); // Restore
	}

	@Override
	public void move() {
		switch (assi) {
			case IDLE:
				look = randomLocation; // Sets the PVector look to the randomLocation

				if (movedToLocation(randomLocation)) {
					randomLocation = new PVector(parent.random(mouseClickPos.x - 50, mouseClickPos.x + 50),
							parent.random(mouseClickPos.y - 50, mouseClickPos.y + 50));
				} else {
					// If the player hasen't moveToLocation the player will move towards it
					moveToLocation(randomLocation);
				}
				break;
			case LOOK:
				// Freeze the movements and start looking around. Later use
				break;
			case MOVE:

				moveToLocation(mouseClickPos);

				if (movedToLocation(mouseClickPos)) {
					assi = Assignment.IDLE; // Sets the assignment to IDLE
				}
				break;
		}
	}

	@Override
	public void moveToLocation(PVector random) {
		if (random.x > location.x) {
			location.x += speed;
		} else if (random.x < location.x) {
			location.x -= speed;
		}

		if (random.y > location.y) {
			location.y += speed;
		} else if (random.y < location.y) {
			location.y -= speed;
		}
	}

	@Override
	public boolean movedToLocation(PVector random) {
		if (location.x - 2 < random.x && location.x + 2 > random.x && location.y - 2 < random.y
				&& location.y + 2 > random.y) {
			return true;
		} else {
			return false;
		}
	}

	public boolean movedToMouseClickPos(PVector mouseClickPos) {
		/*
		 * Need to set this to true so that the walkline doesn't show when the
		 * destination is reached.
		 */
		boolean temp = true;

		// If a new mouseClickPos has been set
		if (isNewMousePos) {
			temp = false;

			// If player location is equal to mouseClickPos but with 4 radius
			if (location.x - 2 < mouseClickPos.x && location.x + 2 > mouseClickPos.x && location.y - 2 < mouseClickPos.y
					&& location.y + 2 > mouseClickPos.y) {

				setIsNewMousePos(false);
				temp = true;
			}
		}
		return temp;
	}

	/*
	 * SETTERS & GETTERS
	 */

	public void setLookPos(int x, int y) {
		look = new PVector(x, y);
	}

	static void setMouseClickPos(int x, int y) {
		mouseClickPos = new PVector(x, y);
		isNewMousePos = true;
	}

	static void setIsNewMousePos(boolean temp) {
		Player.isNewMousePos = temp;
	}

	public void setAssignment(Assignment assi) {
		this.assi = assi;
	}

	public PVector getLocation() {
		return location;
	}
}