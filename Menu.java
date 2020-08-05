package randomGame;

import processing.core.PApplet;
import processing.core.PConstants;

public class Menu {
	PApplet parent;

	private String playCon;

	private int alphaPlay, alphaExit, playWidth;

	private boolean exit;

	Menu(PApplet parent) {
		this.parent = parent;
		
		parent.stroke(1);
		parent.rectMode(PConstants.CENTER);
		parent.textAlign(PConstants.CENTER);
		parent.textSize(50);

		playCon = "Play";
		playWidth = 200;

		alphaPlay = alphaExit = 0;

		exit = false;
	}

	public void main() {
		buttons();
	}

	private void buttons() {
		if (Game.mainMenu) {
			playCon = "Play";
			playWidth = 200;
		} else {
			playCon = "Continue";
			playWidth = 230;
		}
		
		if (mouseOnPlay()) {
			if (parent.mouseButton == PConstants.LEFT) {
				Game.init = false;
				Game.mainMenu = Game.pause = false;
			}

			alphaPlay = 150;
		} else if (mouseOnExit()) {
			if (parent.mouseButton == PConstants.LEFT) {
				exit = true;
			}

			alphaExit = 150;
		} else {
			alphaPlay = alphaExit = 0;
		}

		/*
		 * Play Button
		 */

		// Box
		parent.fill(100, alphaPlay);
		parent.rect(parent.width / 2, parent.height / 4, playWidth, 100);

		// Text
		parent.fill(0);
		parent.text(playCon, parent.width / 2, parent.height / 4 + 15);

		/*
		 * Exit Button
		 */

		// Box
		parent.fill(100, alphaExit);
		parent.rect(parent.width / 2, parent.height / 1.5f, 200, 100);

		// Text
		parent.fill(0);
		parent.text("Exit", parent.width / 2, parent.height / 1.5f + 15);
	}

	public void pause() {
		buttons();
	}

	private boolean mouseOnPlay() {
		if (parent.mouseX >= parent.width / 2 - playWidth / 2 && parent.mouseX <= parent.width / 2 + playWidth / 2
				&& parent.mouseY >= parent.height / 4 - 50 && parent.mouseY <= parent.height / 4 + 50) {
			return true;
		} else {
			return false;
		}
	}

	private boolean mouseOnExit() {
		if (parent.mouseX >= parent.width / 2 - 100 && parent.mouseX <= parent.width / 2 + 100
				&& parent.mouseY >= parent.height / 1.5 - 50 && parent.mouseY <= parent.height / 1.5 + 50) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * GETTERS & SETTERS
	 */

	public boolean isExit() {
		return exit;
	}
}