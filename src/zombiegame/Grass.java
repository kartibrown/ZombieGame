package zombiegame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;

public class Grass {
	PApplet parent;

	private Color grassColor;

	private List<Float> grassAreaPos = new ArrayList<Float>();
	private final int minGrassWidth = 50;
	private final int maxGrassWidth = 500;
	private final int minGrassHeight = 50;
	private final int maxGrassHeight = 500;
	float randomGrassPosX;
	float randomGrassPosY;
	float randomGrassWidth;
	float randomGrassHeight;

	protected Grass(PApplet parent) {
		this.parent = parent;
		
		parent.rectMode(PConstants.CENTER); // Draws the rect from upper left

		grassColor = new Color(0, 200, 0); // Color of the grass

		randomGrassPosX = parent.random(parent.width);
		randomGrassPosY = parent.random(parent.height);
		randomGrassWidth = parent.random(minGrassWidth, maxGrassWidth);
		randomGrassHeight = parent.random(minGrassHeight, maxGrassHeight);
	}

	protected void makeGrass() {
		float x, y, width, height;
		x = grassPos()[0];
		y = grassPos()[1];
		width = grassPos()[2];
		height = grassPos()[3];

		parent.noStroke(); // Makes sure there are no line around objects.
		parent.fill(grassColor.getRGB()); // Draws the color of the grass
		parent.rect(x, y, width, height); // Draws the rectangle
		grassAreaPos = getAreaPos(x, y, width, height);
	}

	/*
	 * Gets the upperleft pos and the lowerright pos in grass area, returns it in a
	 * List
	 */
	private List<Float> getAreaPos(float x, float y, float width, float height) {
		List<Float> sum = new ArrayList<Float>();

		float xx = x - width / 2;
		float yy = y - height / 2;

		float sumX = xx + width;
		float sumY = yy + height;

		sum.add(xx);
		sum.add(yy);
		sum.add(sumX);
		sum.add(sumY);

		return sum;
	}

	// So you can cleanly (in code) pass this through the makeGrass parameter, also returns
	// grass pos
	protected float[] grassPos() {
		return new float[] { randomGrassPosX, randomGrassPosY, randomGrassWidth, randomGrassHeight };
	}

	// Return the List where you can find the upperleft and lowerright position of
	// the grass
	protected List<Float> getGrassAreaPos() {
		return grassAreaPos;
	}
}
