package zombieGame;

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

	private PVector mouseClickPos;

	private int leftHandPosY, rightHandPosY;
	
	public static int numberOfPlayers;

	// Punch
	private int punchRun;
	private boolean whichArm;

	// Color
	private Color armsColor;

	public Player(PApplet parent) {
		this.parent = parent;
		
		parent.ellipseMode(PConstants.CENTER); // Makes the ellipse centered

		// Player stats
		numberOfPlayers=6;
		health = 100;

		// Player pos
		location = new PVector(parent.width / 2, parent.height / 2);

		randomLocation = mouseClickPos = look = location;

		randomLocationGenerated = false;
		rightHandPosY = 0;
		leftHandPosY = 0;
		speed = 1;

		// Controls
		assi = Assignment.IDLE;

		// Punch
		punchRun = 0;
		whichArm = true;

		// Color
		color = new Color(100, 125, 90); // Color of player
		armsColor = new Color(80, 100, 70); // Color of the arms
	}

	//Later
	private void punch() {
		if (whichArm) {
			if (punchRun < 5) {
				rightHandPosY -= 2;
			} else {
				rightHandPosY += 2;
			}
		} else {
			if (punchRun < 5) {
				leftHandPosY -= 2;
			} else {
				leftHandPosY += 2;
			}
		}

		punchRun++;
	}
	
	public void line() {
		//Line between player and mouseClickPos
		parent.line(location.x, location.y, mouseClickPos.x, mouseClickPos.y);
	}

	@Override
	public void render() {
		
		// Rotates towards where the player is walking
		float angle = PApplet.atan2(look.y - location.y, look.x - location.x);

		parent.pushMatrix(); // Saves

		parent.translate(location.x, location.y);
		parent.rotate(angle + PApplet.HALF_PI); // Rotates the player
		
		// Body
		parent.noStroke(); // No black lines around the ellipse
		parent.fill(color.getRGB()); // Sets the color of the player
		parent.ellipse(0, 0, 20, 20); // Creates an ellipse

		// Arms
		parent.stroke(1); // Black lines around the ellipse
		parent.fill(armsColor.getRGB());
		parent.ellipse(0 + 5, 0 + rightHandPosY - 5, 5, 5);
		parent.ellipse(0 - 5, 0 + leftHandPosY - 5, 5, 5);

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

	/*
	 * SETTERS & GETTERS
	 */
	
	public void setLookPos(int x, int y) {
		look = new PVector(x, y);
	}

	public void setMouseClickPos(int x, int y) {
		mouseClickPos = new PVector(x, y);
	}

	public void setAssignment(Assignment assi) {
		this.assi = assi;
	}

	public PVector getLocation() {
		return location;
	}
}