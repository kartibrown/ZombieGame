package zombieGame;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
	private PApplet parent;

	public enum MoveX {
		RIGHT, LEFT, NULL
	}

	public enum MoveY {
		UP, DOWN, NULL
	}

	MoveX moveX;
	MoveY moveY;

	public static PVector location;

	private int speed;

	Camera(PApplet parent) {
		this.parent = parent;

		moveX = MoveX.NULL;
		moveY = MoveY.NULL;

		location = new PVector(0, 0);
		speed = 2;
	}

	public void updateCamera() {
		parent.translate(location.x, location.y);
	}

	/*
	 * THIS IS FOR LATER USE
	 */
	public void cameraFollowPlayer(PVector location) {
		/*
		 * Calculates so the camera follows the player
		 */
		location = new PVector(location.x * -1 + parent.width / 2, location.y * -1 + parent.height / 2);

		parent.translate(location.x, location.y); // Translate the window position
	}

	public void updateKeyControls() {
		switch (moveX) {
		case RIGHT:
			location.x -= speed;
			break;
		case LEFT:
			location.x += speed;
			break;
		case NULL:
			break;
		}

		switch (moveY) {
		case UP:
			location.y += speed;
			break;
		case DOWN:
			location.y -= speed;
			break;
		case NULL:
			break;
		}
	}

	/*
	 * SETTERS & GETTERS
	 */

	public void setMoveX(MoveX moveX) {
		this.moveX = moveX;
	}

	public void setMoveY(MoveY moveY) {
		this.moveY = moveY;
	}
}