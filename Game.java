package zombieGame;

import processing.core.PApplet;
import zombieGame.Camera.MoveX;
import zombieGame.Camera.MoveY;
import zombieGame.Player.Assignment;

public class Game extends PApplet {
	private Menu menu;
	private Player[] player;
	private Camera camera;
	private Field field;

	public static boolean mainMenu, pause, init;

	@Override
	public void settings() {
		size(800, 600, P2D); // Sets the position of the game window
		smooth();
	}

	@Override
	public void setup() {
		player = new Player[15];

		for (int i = 0; i < player.length; i++) {
			player[i] = new Player(this);
		}
		menu = new Menu(this);
		camera = new Camera(this);
		field = new Field(this);

		mainMenu = pause = true;
		init = false;
	}

	@Override
	public void draw() {
		surface.setTitle("Survival game");
		background(200); // Draws the background color

		if (mainMenu || pause) {
			if (!init) {
				/*
				 * Initializing players for the pause- and main menu
				 */
				player = new Player[15];

				for (int i = 0; i < player.length; i++) {
					player[i] = new Player(this);
				}

				init = true;
			}

			// Exits if the exit button is clicked
			if (menu.isExit()) {
				exit();
			}

			// Render the world
			field.render();

			for (int i = 0; i < player.length; i++) {
				player[i].render();
				player[i].move();
			}

			menu.main();

		} else {
			if (!init) {
				/*
				 * Initializing the players to the game
				 */
				player = new Player[200];

				for (int i = 0; i < player.length; i++) {
					player[i] = new Player(this);
				}

				init = true;
			}

			field.render(); // Render the field, the game world

			camera.updateCamera(); // Updates the camera position
			camera.updateKeyControls(); // Updates the camera controls

			for (int i = 0; i < Player.numberOfPlayers; i++) {
				if (!player[i].movedToMouseClickPos(Player.mouseClickPos)) {

					// If players has NOT reached the mouseClickPos then show walkline
					player[i].line();
				}
				player[i].render();
				player[i].move();
			}
		}
	}

	/*
	 * Checks what keys are pressed.
	 */
	@Override
	public void mousePressed() {
		if (mouseButton == LEFT) {

		} else if (mouseButton == RIGHT) {
		}
	}

	@Override
	public void keyPressed() {
		if (key == 'w' || key == 'W')
			camera.setMoveY(MoveY.UP);
		else if (key == 's' || key == 'S')
			camera.setMoveY(MoveY.DOWN);

		if (key == 'd' || key == 'D')
			camera.setMoveX(MoveX.RIGHT);
		else if (key == 'a' || key == 'A')
			camera.setMoveX(MoveX.LEFT);

		if (keyCode == ESC) {
			key = 0; // Resets the key so it doesn't shutdown the app
			pause = true;
			init = false;
		}
	}

	/*
	 * Checks what keys are released. Upon release the player will start moving
	 */
	@Override
	public void mouseReleased() {
		if (mouseButton == LEFT) {
			for (int i = 0; i < Player.numberOfPlayers; i++) {
				player[i].setMouseClickPos((int) (mouseX - Camera.location.x), (int) (mouseY - Camera.location.y));
				player[i].setLookPos((int) (mouseX - Camera.location.x), (int) (mouseY - Camera.location.y));
				player[i].setAssignment(Assignment.MOVE);
			}
		} else if (mouseButton == RIGHT) {
		}
	}

	@Override
	public void keyReleased() {
		if (key == 'w' || key == 'W')
			camera.setMoveY(MoveY.NULL);
		else if (key == 's' || key == 'S')
			camera.setMoveY(MoveY.NULL);

		if (key == 'd' || key == 'D')
			camera.setMoveX(MoveX.NULL);
		else if (key == 'a' || key == 'A')
			camera.setMoveX(MoveX.NULL);
	}

	public static void main(String[] args) {
		String[] processingArgs = { "Game" }; // Processing default arguments
		Game mySketch = new Game();
		PApplet.runSketch(processingArgs, mySketch);
	}
}